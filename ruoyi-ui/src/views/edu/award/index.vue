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
      <el-form-item label="竞赛" prop="contestId">
        <el-select v-model="queryParams.contestId" placeholder="请选择竞赛" clearable size="small">
          <el-option
            v-for="item in contestOptions"
            :key="item.contestId"
            :label="item.contestName"
            :value="item.contestId"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="等级" prop="grade">
        <el-select v-model="queryParams.grade" placeholder="请选择等级" clearable size="small">
          <el-option
            v-for="dict in gradeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="获奖时间" prop="issueTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.issueTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择获奖时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="指导老师" prop="teacherName">
        <el-input
          v-model="queryParams.teacherName"
          placeholder="请输入指导老师"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="团队名称" prop="teamName">
        <el-input
          v-model="queryParams.teamName"
          placeholder="请输入团队名称"
          clearable
          size="small"
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
          v-hasPermi="['edu:award:add']"
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
          v-hasPermi="['edu:award:edit']"
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
          v-hasPermi="['edu:award:remove']"
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
          v-hasPermi="['edu:award:export']"
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
          v-hasPermi="['edu:award:export']"
        >下载附件</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="awardList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="奖项ID" align="center" prop="awardId" />
      <el-table-column label="学生姓名" align="center" prop="student.studentName" />
      <el-table-column label="竞赛" align="center" prop="contest.contestName" />
      <el-table-column label="等级" align="center" prop="grade">
        <template slot-scope="scope">
          <dict-tag :options="gradeOptions" :value="scope.row.grade"/>
        </template>
      </el-table-column>
      <el-table-column label="获奖时间" align="center" prop="issueTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.issueTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="指导老师" align="center" prop="teacherName" />
      <el-table-column label="团队名称" align="center" prop="teamName" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['edu:award:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['edu:award:remove']"
          >删除</el-button>
          <el-button
            type="text"
            size="small"
            icon="el-icon-download"
            @click="handleDownload(scope.row)"
            v-hasPermi="['edu:award:export']"
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

    <!-- 添加或修改奖项对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="学生" prop="studentId">
          <el-select v-model="form.studentId" placeholder="请输入姓名"
            filterable
            clearable
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
        <el-form-item label="竞赛" prop="contestId">
          <el-select clearable v-model="form.contestId" placeholder="请选择竞赛">
            <el-option
              v-for="item in contestOptions"
              :key="item.contestId"
              :label="item.contestName"
              :value="item.contestId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="等级" prop="grade">
          <el-select v-model="form.grade" placeholder="请选择等级">
            <el-option
              v-for="dict in gradeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="获奖时间" prop="issueTime">
          <el-date-picker clearable size="small"
            v-model="form.issueTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择获奖时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="指导老师" prop="teacherName">
          <el-input v-model="form.teacherName" placeholder="请输入指导老师" />
        </el-form-item>
        <el-form-item label="附件">
          <fileUpload v-model="form.fileUrl"/>
        </el-form-item>
        <el-form-item label="团队名称" prop="teamName">
          <el-input v-model="form.teamName" placeholder="请输入团队名称" />
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
import { listAward, getAward, delAward, addAward, updateAward, exportAward } from "@/api/edu/award";
import { listStudent } from "@/api/edu/student";
import { listContest } from "@/api/edu/contest";
import { downLoadZip } from "@/utils/zipdownload";

export default {
  name: "Award",
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
      // 奖项表格数据
      awardList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 等级字典
      gradeOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        studentId: null,
        contestId: null,
        grade: null,
        issueTime: null,
        teacherName: null,
        teamName: null,
      },
      // 学生选项
      studentOptions: [],
      // 竞赛选项
      contestOptions: [],
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getContestOptions();
    this.getDicts("edu_award_grade").then(response => {
      this.gradeOptions = response.data;
    });
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
    /** 获取竞赛选项 */
    getContestOptions() {
      listContest().then(response => {
        this.contestOptions = response.rows;
      });
    },
    /** 查询奖项列表 */
    getList() {
      this.loading = true;
      listAward(this.queryParams).then(response => {
        this.awardList = response.rows;
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
        awardId: null,
        studentId: null,
        contestId: null,
        grade: null,
        issueTime: null,
        teacherName: null,
        fileUrl: null,
        teamName: null,
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
      this.ids = selection.map(item => item.awardId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加奖项";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const awardId = row.awardId || this.ids
      getAward(awardId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改奖项";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.awardId != null) {
            updateAward(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAward(this.form).then(response => {
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
      const awardIds = row.awardId || this.ids;
      this.$confirm('是否确认删除奖项编号为"' + awardIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delAward(awardIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 下载附件操作 */
    handleDownload(row) {
      const awardIds = row.awardId || this.ids;
      if (awardIds == "") {
        this.msgError("请选择要下载的数据");
        return;
      }
      downLoadZip("/common/batchDownload?bizType=eduAward&bizIds=" + awardIds, "ruoyi");
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有奖项数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportAward(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
