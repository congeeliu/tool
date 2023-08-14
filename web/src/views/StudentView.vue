<template>
  <div class="main">
    <div class="main-header" :style="otherStyles">
      <!-- <header-nav /> -->
    </div>

    <div class="main-content common-scrollbar" :style="otherStyles">
      <div style="height: 100%">
        <div class="formCard-content">

          <Card :bordered="false" :dis-hover="true" :shadow="false" class="formCard" style="margin-left: -25px;">
            <form>
              <div class="row">
                <div class="col-3 col-md-2 col-lg-1">
                  <label for="name" class="col-form-label" style="margin-left: 20px;">企业名称：</label>
                </div>
                <div class="col-9 col-md-4 col-lg-2">
                  <el-input v-model="findMessage.name" type="text" id="name" placeholder="请输入企业名称" />
                </div>
                <div class="col-3 col-md-2 col-lg-1">
                  <label for="visitor" class="col-form-label" style="margin-left: 20px;">联系人：</label>
                </div>
                <div class="col-9 col-md-4 col-lg-2">
                  <el-input v-model="findMessage.people" type="text" id="visitor" placeholder="请输入走访人" />
                </div>
                <div class="col-3 col-md-2 col-lg-1">
                  <label for="result" class="col-form-label" style="margin-left: 20px;">反馈结果：</label>
                </div>
                <div class="col-9 col-md-3 col-lg-2">
                  <el-select v-model="findMessage.result" id="result" placeholder="请输入反馈结果">
                    <el-option value="处理中">处理中</el-option>
                    <el-option value="已办结">已办结</el-option>
                    <!-- <el-option value="无异常">无异常</el-option> -->
                  </el-select>
                </div>
                <!-- <div class="col-3 col-md-2 col-lg-1">
                  <label for="date" class="col-form-label" style="margin-left: 20px;">走访时间：</label>
                </div>
                <div class="col-9 col-md-4 col-lg-2">
                  <el-date-picker v-model="findMessage.date" type="daterange" start-placeholder="开始时间"
                    end-placeholder="结束时间" :default-value="new Date()" value-format="yyyy-MM-dd" style="width: 100%;" />
                </div> -->
                <div>
                  <div class="find-area">
                    <Button type="primary" class="btn-find m-2" @click="findInterview(true)"
                      style="margin-right: 8px; color: white; background-color: #1890FF; border-radius: 0%;">查询</Button>
                    <Button class="btn-reset m-2" @click="reset()"
                      style="margin-right: 8px; border-radius: 0%;">重置</Button>
                  </div>
                </div>
                <!-- </div> -->

              </div>
            </form>
          </Card>

          <div :bordered="false" :dis-hover="true" :shadow="false" class="formCard"
            style="margin-top: 20px; margin-left: -20px;">
            <table class="table" style="text-align: center; line-height: 35px">
              <thead>
                <tr style="background-color: #F8F8F9;">
                  <th>企业名称</th>
                  <!-- <th>问题描述</th> -->
                  <th>联系人</th>
                  <th>联系电话</th>
                  <th>反馈结果</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="interview in data" :key="interview.id" style="background-color: white;">
                  <td>{{ interview.name }}</td>
                  <!-- <td>{{ interview.date }}</td> -->
                  <td>{{ interview.people }}</td>
                  <td>{{ interview.telephone }}</td>
                  <td>
                    <div v-if="interview.result === '已办结'" style="color: #4CA9FF;">
                      {{ interview.result }}
                    </div>
                    <div v-if="interview.result === '处理中'" style="color: #FF2B18;">
                      {{ interview.result }}
                    </div>
                    <!-- <div v-if="interview.result === '无异常'" style="color: #42D32B;">
                      {{ interview.result }}
                    </div> -->

                  </td>
                  <td>

                    <!-- Form -->
                    <a @click="openDialog(interview)">
                      <div v-if="interview.result === '处理中'" style="color: #FF2B18;">办结</div>
                      <div v-else :style="{ color: (interview.result === '已办结' ? '#4CA9FF' : '#42D32B') }">详情</div>
                    </a>

                  </td>
                </tr>
              </tbody>
            </table>

            <div style="float: right;">
              <Page :total="pageData.total" :page-size="pageData.size" :current="pageData.page" show-elevator show-sizer
                show-total @on-page-size-change="changePage($event, 'size')" @on-change="changePage($event, 'page')"
                style="margin-top: 20px;" />
            </div>

            <!-- <el-dialog title="办结信息" :v-model:propName="dialogFormVisible" center>
              <el-form>
                <el-row type="flex" :gutter="20" style="margin-left: 90px;">
                  <el-col :span="9">
                    <el-form-item label="企业名称" :label-width="formLabelWidth">
                      <el-input v-model="dialog.name" class="dialog-input" disabled></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>

                <el-row type="flex" justify="center" :gutter="20">
                  <el-col :span="9">
                    <el-form-item label="联系人" :label-width="formLabelWidth">
                      <el-input v-model="dialog.people" class="dialog-input" disabled></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="9">
                    <el-form-item label="联系人电话" :label-width="formLabelWidth">
                      <el-input v-model="dialog.telephone" class="dialog-input" disabled></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>

                <el-row type="flex" style="margin-left: 105px;">
                  <el-col :span="20">
                    <el-form-item label="问题描述">
                      <el-input v-model="dialog.description" class="dialog-input" :rows="4" type="textarea" disabled />
                    </el-form-item>
                  </el-col>
                </el-row>

                <el-row type="flex" style="margin-left: 105px;">
                  <el-form-item label="问题是否解决">
                    <el-radio-group v-model="dialog.isSolved" class="ml-4 dialog-input"
                      style="float: left; margin-top: 10px;" :disabled="dialog.result !== '处理中'">
                      <el-radio label="是">是</el-radio>
                      <el-radio label="否">否</el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-row>

                <el-row type="flex" style="margin-left: 105px;">
                  <el-col :span="20">
                    <el-form-item label="备注">
                      <el-input v-model="dialog.remarks" class="dialog-input" :rows="4" type="textarea"
                        :placeholder="dialog.result !== '处理中' ? '' : '请输入备注'" :disabled="dialog.result !== '处理中'" />
                    </el-form-item>
                    <div v-if="remarkAlert" style="color: red;">
                      当问题是否解决选择为否时，备注项必填
                    </div>
                  </el-col>
                </el-row>

              </el-form>

              <div slot="footer" class="dialog-footer">
                <Button v-if="dialog.result === '处理中'" @click="updateInterview(dialog)" type="primary"
                  style="background-color: #1890FF; border-radius: 0%;">提交</Button>
              </div>
            </el-dialog> -->

          </div>

        </div>
      </div>
    </div>

  </div>
</template>

<script>
import { ref } from 'vue';

export default {
  name: 'StudentView',
  components: {
    // 'header-nav': () => import('./components/header-nav'),
  },
  data() {
    return {
      // imgUrl: "http://localhost:9000/img/",
      data: [],
      pageData: {
        total: 0,
        size: 10,
        page: 1
      },
      findMessage: {
        name: "",
        people: "",
        result: "",
        // date: ref([]),
      },
      flag: 0,
      remarkAlert: false,
      isSolvedAlert: false,
      dialogFormVisible: false,
      dialog: {
        id: '',
        name: '',
        // position: '',
        people: '',
        telephone: '',
        // date: '',
        // photo: '',
        description: '',
        // appeal: '',
        isSolved: '',
        remarks: '',
        result: '',
        // kind: '',
        // isSolvedOnSite: '',
      }
    }
  },

  computed: {
    // logoImgStyles() {
    //   return {
    //     backgroundImage: `url(${this.shrink ? require('../../assets/logo-min.png') : require('../../assets/logo-white.png')})`,
    //     padding: this.shrink ? '1px' : '1px 1px 1px 1px'
    //     // backgroundSize: '85%'
    //   }
    // },

    // otherStyles() {
    //   return {
    //     left: this.shrink ? '1px' : '1px'
    //   }
    // }
  },

  mounted() {
    this.queryList(true)
  },

  methods: {
    changePage(e, type) {
      if (type === 'size') {
        this.pageData.size = e
      } else if (type === 'page') {
        this.pageData.page = e
      }
      if (this.flag === 0) {
        this.queryList(false)
      } else {
        this.findInterview(false)
      }
    },

    queryList(flag) {
      this.flag = 0
      if (flag) {
        this.pageData.page = 1
      }
      let obj = {}
      let params = Object.assign(obj, {
        page: this.pageData.page - 1,
        size: this.pageData.size,
      })
      this.$http.get('/api/information/enterpriseParams', {
        params: params
      }).then(res => {
        this.data = res.data.enterpriseParams
        this.pageData.total = res.data.recordsCount
      }).catch(() => {
        this.$Message.error('列表查询失败')
      })
    },

    findInterview(flag) {
      this.flag = 1
      if (flag) {
        this.pageData.page = 1
      }
      if (this.findMessage.date === null) {
        this.findMessage.date = ref([])
      }
      const params = {
        name: this.findMessage.name,
        people: this.findMessage.people,
        result: this.findMessage.result,
        // date: this.findMessage.date[0] + ";" + this.findMessage.date[1],
        page: this.pageData.page - 1,
        size: this.pageData.size,
      }

      this.$http.get('/api/information/enterpriseParams/find', {
        params: params,
      }).then(res => {
        this.data = res.data.enterpriseParams
        this.pageData.total = res.data.recordsCount
        this.$Message.success('查询成功')
      }).catch(() => {
        this.$Message.error('查询失败')
      })

    },

    updateInterview(interview) {
      if (interview.isSolved === "" || interview.isSolved === null) {
        this.isSolvedAlert = true
      }
      else if (interview.isSolved === "否" && (interview.remarks === "" || interview.remarks === null)) {
        this.remarkAlert = true
        // alert("当是否解决企业诉求选择为否时，备注项必填")
      }
      else {
        this.remarkAlert = false
        this.isSolvedAlert = false
        if (interview.isSolved === "是") {
          interview.result = "已办结"
        } else {
          // interview.result = "未解决"
          // interview.result = "处理中"
          interview.result = "已办结"
        }
        this.$http.put('/api/information/enterpriseParams/update', interview).then(() => {
          // Modal.getInstance('#update-interview-modal-' + interview.id).hide()
          this.dialogFormVisible = false
          this.queryList()
          this.$Message.success('更新成功')
          // console.log("success")
        }).catch((res) => {
          console.log(res);
          this.$Message.error('更新失败')
        })
      }
    },

    openDialog(interview) {
      this.dialogFormVisible = true
      this.dialog = interview
    },

    reset() {
      this.findMessage.name = ""
      this.findMessage.visitor = ""
      this.findMessage.result = ""
      this.findMessage.date = ""
      this.queryList(true)
    }

  },

}
</script>

<style scoped>
.col-10 {
  margin-bottom: 10px;
}

.col-9 {
  margin-bottom: 10px;
}

img {
  width: 150px;
  height: 150px;
  margin-right: 5px;
  margin-bottom: 5px;
}

.find-area {
  float: right;
}

.dialog-input {
  -webkit-text-fill-color: gray;
}
</style>