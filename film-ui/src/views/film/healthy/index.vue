<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="用户名称" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入用户名称"
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
      <el-form-item label="手机" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入手机"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="目前体温" prop="temperatureStatus">
        <el-select
          v-model="queryParams.temperatureStatus"
          placeholder="请选择目前体温"
          clearable
        >
          <el-option
            v-for="dict in dict.type.film_temperature_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="身体情况" prop="bodyStatus">
        <el-select
          v-model="queryParams.bodyStatus"
          placeholder="请选择身体情况"
          clearable
        >
          <el-option
            v-for="dict in dict.type.film_body_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="健康码颜色" prop="healthCodeColor">
        <el-select
          v-model="queryParams.healthCodeColor"
          placeholder="请选择健康码颜色"
          clearable
        >
          <el-option
            v-for="dict in dict.type.film_health_code_color"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="疫苗接种情况" prop="inoculationStatus">
        <el-select
          v-model="queryParams.inoculationStatus"
          placeholder="请选择疫苗接种情况"
          clearable
        >
          <el-option
            v-for="dict in dict.type.film_inoculation_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['film:healthy:add']"
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
          v-hasPermi="['film:healthy:edit']"
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
          v-hasPermi="['film:healthy:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['film:healthy:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="healthyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="用户名称" align="center" prop="userName" />
      <el-table-column label="用户昵称" align="center" prop="nickName" />
      <el-table-column label="手机" align="center" prop="phone" />
      <el-table-column label="目前体温" align="center" prop="temperatureStatus" width="150">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.film_temperature_status" :value="scope.row.temperatureStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="身体情况" align="center" prop="bodyStatus" width="150">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.film_body_status" :value="scope.row.bodyStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="健康码颜色" align="center" prop="healthCodeColor" width="150">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.film_health_code_color" :value="scope.row.healthCodeColor"/>
        </template>
      </el-table-column>
      <el-table-column label="疫苗接种情况" align="center" prop="inoculationStatus" width="150">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.film_inoculation_status" :value="scope.row.inoculationStatus"/>
        </template>
      </el-table-column>
      <!-- <el-table-column label="健康码截图" align="center" prop="img" >
        <template slot-scope="scope">
            <el-image style="width: 100px; height: 100px" :src="baseUrl+scope.row.img"></el-image>
        </template>
      </el-table-column> -->
      <el-table-column label="上报时间" align="center" prop="createTime" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['film:healthy:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['film:healthy:remove']"
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

    <!-- 添加或修改健康码管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="用户名称" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入用户名称" />
        </el-form-item>
        <el-form-item label="用户昵称" prop="nickName">
          <el-input v-model="form.nickName" placeholder="请输入用户昵称" />
        </el-form-item>
        <el-form-item label="手机" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入手机" />
        </el-form-item>
        <el-form-item label="目前体温" prop="temperatureStatus">
          <el-select
            v-model="form.temperatureStatus"
            placeholder="请选择目前体温"
            clearable
          >
            <el-option
              v-for="dict in dict.type.film_temperature_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
      </el-form-item>
      <el-form-item label="身体情况" prop="bodyStatus">
        <el-select
          v-model="form.bodyStatus"
          placeholder="请选择身体情况"
          clearable
        >
          <el-option
            v-for="dict in dict.type.film_body_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="健康码颜色" prop="healthCodeColor">
        <el-select
          v-model="form.healthCodeColor"
          placeholder="请选择健康码颜色"
          clearable
        >
          <el-option
            v-for="dict in dict.type.film_health_code_color"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="疫苗接种情况" prop="inoculationStatus">
        <el-select
          v-model="form.inoculationStatus"
          placeholder="请选择疫苗接种情况"
          clearable
        >
          <el-option
            v-for="dict in dict.type.film_inoculation_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
        <!-- <el-form-item label="健康码截图" prop="img">
          <el-input v-model="form.img" placeholder="请输入健康码截图" />
          <ImageUpload  v-model="form.img" :limit="1" :fileSize="5"/>
        </el-form-item> -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listHealthy, getHealthy, delHealthy, addHealthy, updateHealthy } from "@/api/film/healthy";

export default {
  name: "Healthy",
  dicts: ["film_temperature_status", "film_health_code_color","film_body_status","film_inoculation_status"],
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
      // 健康码管理表格数据
      healthyList: [],
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
        phone: null,
        img: null,
        temperatureStatus: null,
        healthCodeColor: null,
        bodyStatus: null,
        inoculationStatus: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询健康码管理列表 */
    getList() {
      this.loading = true;
      listHealthy(this.queryParams).then(response => {
        console.log(response.rows)
        this.healthyList = response.rows;
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
        phone: null,
        img: null,
        temperatureStatus: null,
        healthCodeColor: null,
        bodyStatus: null,
        inoculationStatus: null
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
      this.title = "添加健康码管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getHealthy(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改健康码管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateHealthy(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addHealthy(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除健康码管理编号为"' + ids + '"的数据项？').then(function() {
        return delHealthy(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('film/healthy/export', {
        ...this.queryParams
      }, `healthy_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
