<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="电影名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入电影名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="英文名称" prop="eName">
        <el-input
          v-model="queryParams.eName"
          placeholder="请输入英文名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
     <el-form-item label="类型" prop="type">
        <el-select
          v-model="queryParams.type"
          placeholder="请选择电影类型"
          clearable
        >
          <el-option
            v-for="dict in dict.type.film_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="版本" prop="version">
        <el-select
          v-model="queryParams.version"
          placeholder="请选择电影版本"
          clearable
        >
          <el-option
            v-for="dict in dict.type.film_version"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="请选择电影状态"
          clearable
        >
          <el-option
            v-for="dict in dict.type.film_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
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
          v-hasPermi="['film:film:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['film:film:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['film:film:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['film:film:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="filmList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="电影编号" align="center" prop="id"  width="120" />
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
      <el-table-column label="想看人数" align="center" prop="pNumber"  width="150" />
      <el-table-column label="片源" align="center" prop="version" width="150">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.film_source" :value="scope.row.src"/>
        </template>
      </el-table-column>
      <el-table-column label="语音" align="center" prop="language"  width="150" />
      <el-table-column label="是否上映" align="center" prop="released" width="150">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.film_released" :value="scope.row.released+''"/>
        </template>
      </el-table-column>
      <el-table-column label="上映时间" align="center" prop="releaseDate"  width="180" />
      <el-table-column label="状态" align="center" prop="status" width="150">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.film_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
        width="150"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['film:film:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['film:film:remove']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改电影 -->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="电影名称" prop="name">
        <el-input
          v-model="form.name"
          placeholder="请输入电影名称"
          clearable
        />
      </el-form-item>
      <el-form-item label="英文名称" prop="eName">
        <el-input
          v-model="form.eName"
          placeholder="请输入英文名称"
          clearable
        />
      </el-form-item>
      <el-form-item label="类型" prop="type">
          <el-select
            v-model="form.type"
            placeholder="请选择电影类型"
            clearable
          >
            <el-option
              v-for="dict in dict.type.film_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="导演" prop="director">
          <el-input v-model="form.director" placeholder="请输入导演名称" />
        </el-form-item>
        <el-form-item label="电影图片" prop="director">
        <ImageUpload  v-model="form.vImg" :limit="1" :fileSize="5"/>
        </el-form-item>
        <el-form-item label="电影海报图片" prop="director">
        <ImageUpload  v-model="form.poster" :limit="1" :fileSize="5"/>
        </el-form-item>
        <el-form-item label="宣传海报图片" prop="director">
          <ImageUpload  v-model="form.pImg" :limit="1" :fileSize="5"/>
        </el-form-item>
        <el-form-item label="明星演员" prop="star">
          <el-input v-model="form.star" placeholder="请输入明星演员" />
        </el-form-item>
        <el-form-item label="版本" prop="version">
          <el-select
            v-model="form.version"
            placeholder="请选择电影版本"
            clearable
          >
            <el-option
              v-for="dict in dict.type.film_version"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="剧情描述" prop="describe">
          <el-input v-model="form.describe" placeholder="请输入剧情描述" type="textarea"/>
        </el-form-item>
        <el-form-item label="评分" prop="score">
          <el-input v-model="form.score" placeholder="请输入评分" />
        </el-form-item>
        <el-form-item label="想看人数" prop="pNumber">
          <el-input v-model="form.pNumber" placeholder="请输入想看人数" />
        </el-form-item>
        <el-form-item label="片源" prop="src">
          <el-select
            v-model="form.src"
            placeholder="请选择片源"
            clearable
          >
            <el-option
              v-for="dict in dict.type.film_source"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="语言" prop="language">
          <el-input v-model="form.language" placeholder="请输入语言" />
        </el-form-item>
        <el-form-item label="电影时长" prop="duration">
          <el-input v-model.number="form.duration" placeholder="请输入电影时长" />
        </el-form-item>
        <el-form-item label="是否上映" prop="released">
          <el-select
            v-model="form.released"
            placeholder="请选择是否上映"
            clearable
          >
            <el-option
              v-for="dict in dict.type.film_released"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="上映时间" prop="releaseDate">
          <el-date-picker
            v-model="form.releaseDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select
            v-model="form.status"
            placeholder="请选择电影状态"
            clearable
          >
            <el-option
              v-for="dict in dict.type.film_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="电影票价" prop="price">
          <el-input v-model="form.price" placeholder="请输入电影票价" />
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
import { filmList,addFilm,uodateFilm,deleteFilm,getFilm } from "@/api/film/film";
export default {
  name: "film",
  dicts: ["film_type", "film_version","film_released","film_source","film_status"],
  data() {
    return {
      filmList: [],
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
      // 巡检任务明细表格数据
      // billInspectTaskListList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        eName: null,
        type: null,
        director: null,
        vImg: null,
        poster: null,
        pImg: null,
        star: null,
        version: null,
        describe: null,
        score: null,
        pNumber: null,
        src: null,
        language: null,
        released: null,
        releaseDate: null,
        status:null,
        price:null,
      },
      baseUrl:process.env.VUE_APP_BASE_API,
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [{required:true,message:'电影名称不能为空',trigger:'blur'}],
        eName: [{required:true,message:'英文名称不能为空',trigger:'blur'}],
        type: [{required:true,message:'请选择类型',trigger:'blur'}],
        director: [{required:true,message:'导演不能为空',trigger:'blur'}],
        vImg: [{required:true,message:'请选择电影图片',trigger:'blur'}],
        poster: [{required:true,message:'请选择电影海报',trigger:'blur'}],
        pImg: [{required:true,message:'请选择电影宣传图',trigger:'blur'}],
        star: [{required:true,message:'明星演员不能为空',trigger:'blur'}],
        version: [{required:true,message:'请选择版本',trigger:'blur'}],
        describe: [{required:true,message:'描述不能为空',trigger:'blur'}],
        score: [{required:true,message:'评分不能为空',trigger:'blur'}],
        pNumber: [{required:true,message:'想看人数不能为空',trigger:'blur'}],
        src: [{required:true,message:'请选择片源',trigger:'blur'}],
        language: [{required:true,message:'语言不能为空',trigger:'blur'}],
        duration: [{required:true,message:'时长不能为空',trigger:'blur'}],
        released: [{required:true,message:'请选择上映',trigger:'blur'}],
        releaseDate: [{required:true,message:'上映时间不能为空',trigger:'blur'}],
        price: [{required:true,message:'电影票价不能为空',trigger:'blur'}],
        status: [{required:true,message:'请选择影片状态',trigger:'blur'}],
      },
    };
  },
  created() {  
    this.getList();
  },
  methods: {
    /** 查询电影列表 */
    getList() {
      this.loading = true;
      filmList(this.queryParams).then((response) => {
        this.filmList = response.rows;
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
        name: null,
        eName: null,
        type: null,
        director: null,
        vImg: null,
        poster: null,
        pImg: null,
        star: null,
        version: null,
        describe: null,
        score: null,
        pNumber: null,
        src: null,
        language: null,
        released: null,
        releaseDate: null,
        status:null,
        price:null,
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
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加电影信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getFilm(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改电影信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            uodateFilm(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFilm(this.form).then((response) => {
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
      this.$modal.confirm('是否确认删除电影编号为"' + ids + '"的数据项？').then(function () {
        return deleteFilm(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download("film/film/export",{
          ...this.queryParams,
      },`film_${new Date().getTime()}.xlsx`);
    },
  },
};
</script>
