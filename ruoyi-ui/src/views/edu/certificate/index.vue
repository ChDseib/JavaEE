<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="学生" prop="studentId">
        <el-select v-model="queryParams.studentId" placeholder="请输入姓名" clearable size="small"
          filterable
          remote
          :remote-method="searchStudent"
          :loading="loading">
          <el-option
            v-for="item in studentOptions"
            :key="item.studentId"
            :label="item.studentName"
            :value="item.studentId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="认证机构" prop="authorityId">
        <el-select v-model="queryParams.authorityId" placeholder="请选择认证机构" clearable size="small">
          <el-option
            v-for="item in authorityOptions"
            :key="item.authorityId"
            :label="item.authorityName"
            :value="item.authorityId"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="证书名称" prop="certificateName">
        <el-input
          v-model="queryParams.certificateName"
          placeholder="请输入证书名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="认证时间" prop="issueTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.issueTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择认证时间">
        </el-date-picker>
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
          v-hasPermi="['edu:certificate:add']"
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
          v-hasPermi="['edu:certificate:edit']"
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
          v-hasPermi="['edu:certificate:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['edu:certificate:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-paperclip"
          size="mini"
          :loading="exportLoading"
          @click="handleDownload"
          v-hasPermi="['edu:certificate:export']"
        >下载附件</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="certificateList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="学生证书ID" align="center" prop="certificateId" />
      <el-table-column label="学生姓名" align="center" prop="student.studentName" />
      <el-table-column label="认证机构" align="center" prop="authority.authorityName" />
      <el-table-column label="证书名称" align="center" prop="certificateName" />
      <el-table-column label="认证时间" align="center" prop="issueTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.issueTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['edu:certificate:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['edu:certificate:remove']"
          >删除</el-button>
          <el-button
            type="text"
            size="small"
            icon="el-icon-download"
            @click="handleDownload(scope.row)"
            v-hasPermi="['edu:certificate:export']"
          >下载附件</el-button>
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

    <!-- 添加或修改学生证书对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="学生" prop="studentId">
          <el-select v-model="form.studentId" placeholder="请输入姓名"
            filterable
            remote
            reserve-keyword
            :remote-method="searchStudent"
            :loading="loading">
            <el-option
              v-for="item in studentOptions"
              :key="item.studentId"
              :label="item.studentName"
              :value="item.studentId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="认证机构" prop="authorityId">
          <el-select v-model="form.authorityId" placeholder="请选择认证机构">
            <el-option
              v-for="item in authorityOptions"
              :key="item.authorityId"
              :label="item.authorityName"
              :value="item.authorityId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="证书名称" prop="certificateName">
          <el-input v-model="form.certificateName" placeholder="请输入证书名称" />
        </el-form-item>
        <el-form-item label="认证时间" prop="issueTime">
          <el-date-picker clearable size="small"
            v-model="form.issueTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择认证时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="附件">
          <fileUpload v-model="form.fileUrl"/>
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
import { listCertificate, getCertificate, delCertificate, addCertificate, updateCertificate, exportCertificate } from "@/api/edu/certificate";
import { listStudent } from "@/api/edu/student";
import { listAuthority } from "@/api/edu/authority";
import { downLoadZip } from "@/utils/zipdownload";

export default {
  name: "Certificate",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
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
      // 学生证书表格数据
      certificateList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        studentId: null,
        authorityId: null,
        certificateName: null,
        issueTime: null,
      },
      // 学生选项
      studentOptions: [],
      // 认证机构选项
      authorityOptions: [],
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getAuthorityOptions();
  },
  methods: {
    /** 按姓名查询学生 */
    searchStudent(query) {
      if (query !== '') {
        this.loading = true;
        setTimeout(() => {
          listStudent({ studentName: query }).then(response => {
            this.studentOptions = response.rows.filter(item => {
              return item.studentName.toLowerCase().indexOf(query.toLowerCase()) > -1;
            });
            this.loading = false;
          });
        }, 200);
      } else {
        this.studentOptions = [];
      }
    },
    /** 获取认证机构选项 */
    getAuthorityOptions() {
      listAuthority().then(response => {
        this.authorityOptions = response.rows;
      });
    },
    /** 查询学生证书列表 */
    getList() {
      this.loading = true;
      listCertificate(this.queryParams).then(response => {
        this.certificateList = response.rows;
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
        certificateId: null,
        studentId: null,
        authorityId: null,
        certificateName: null,
        issueTime: null,
        fileUrl: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
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
      this.ids = selection.map(item => item.certificateId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加学生证书";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const certificateId = row.certificateId || this.ids
      getCertificate(certificateId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改学生证书";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.certificateId != null) {
            updateCertificate(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCertificate(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const certificateIds = row.certificateId || this.ids;
      this.$confirm('是否确认删除学生证书编号为"' + certificateIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delCertificate(certificateIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 下载附件操作 */
    handleDownload(row) {
      const certificateIds = row.certificateId || this.ids;
      if (certificateIds == "") {
        this.msgError("请选择要下载的数据");
        return;
      }
      downLoadZip("/common/batchDownload?bizType=eduCertificate&bizIds=" + certificateIds, "ruoyi");
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有学生证书数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportCertificate(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
