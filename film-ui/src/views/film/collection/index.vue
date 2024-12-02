<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户名" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入用户名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户昵称" prop="nickName">
        <el-input
          v-model="queryParams.nickName"
          placeholder="请输入用户昵称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['film:collection:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['film:collection:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['film:collection:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['film:collection:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="collectionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="收藏编号" align="center" prop="id" />
      <el-table-column label="用户名" align="center" prop="userName" />
      <el-table-column label="用户昵称" align="center" prop="nickName" />
      <el-table-column label="电影图片" align="center" prop="vImg"  width="120">
        <template slot-scope="scope">
            <el-image style="width: 100px; height: 100px" :src="baseUrl+scope.row.vImg"></el-image>
        </template>
      </el-table-column>
      <el-table-column label="名称" align="center" prop="name"  width="150" />
      <el-table-column label="英文名称" align="center" prop="eName"  width="150" />
      <el-table-column label="类型" align="center" prop="type" width="150">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.film_type" :value="scope.row.type"/>
        </template>
      </el-table-column>
      <el-table-column label="导演" align="center" prop="director"  width="150" />
      <el-table-column label="明星演员" align="center" prop="star"  width="150" />
      <el-table-column label="版本" align="center" prop="version" width="150">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.film_version" :value="scope.row.version"/>
        </template>
      </el-table-column>
      <el-table-column label="电影票价(元)" align="center" prop="price"  width="150" />
      <el-table-column label="评分" align="center" prop="score"  width="150" />
      <el-table-column label="片源" align="center" prop="version" width="150">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.film_source" :value="scope.row.src"/>
        </template>
      </el-table-column>
      <el-table-column label="语音" align="center" prop="language"  width="150" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="150">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['film:collection:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['film:collection:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改收藏对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户名" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="用户昵称" prop="nickName">
          <el-input v-model="form.nickName" placeholder="请输入用户昵称" />
        </el-form-item>
        <el-form-item label="影片" prop="filmId">
          <!-- <el-input v-model="form.filmId" placeholder="请输入影片id" /> -->
          <el-select
            v-model="form.filmId"
            placeholder="请选择影片"
            clearable
          >
            <el-option
              v-for="item in film"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCollection, getCollection, delCollection, addCollection, updateCollection } from "@/api/film/collection";
import {getFilmBuildOption } from "@/api/film/film";
export default {
  name: "Collection",
  dicts: ["film_type", "film_version","film_released","film_source","film_status"],
  data() {
    return {
      baseUrl:process.env.VUE_APP_BASE_API,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 收藏表格数据
      collectionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userName: null,
        nickName: null,
        filmId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      film:[],
    };
  },
  created() {
    this.getList();
    this.getFilmBuildOption();
  },
  methods: {
    getFilmBuildOption(){
      getFilmBuildOption().then(res=>{
        this.film=res
      })
    },
    /** 查询收藏列表 */
    getList() {
      this.loading = true;
      listCollection(this.queryParams).then(response => {
        this.collectionList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        userName: null,
        nickName: null,
        filmId: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加收藏";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCollection(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改收藏";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCollection(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCollection(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除收藏编号为"' + ids + '"的数据项？').then(function() {
        return delCollection(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('film/collection/export', {
        ...this.queryParams
      }, `collection_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
