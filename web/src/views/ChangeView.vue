<template>
  <CardBase>
    <form>
      <div class="row mb-3">
        <label for="find" class="col-1 col-form-label">查询</label>
        <div class="col-3">
          <input v-model="findContent.name" type="text" class="form-control" id="find">
        </div>
        <div class="col-3">
          <button @click="pullPage(1)" type="button" class="btn btn-primary m-1">查询</button>
          <button type="button" class="btn btn-primary m-1" data-bs-toggle="modal" data-bs-target="#add-modal">新增</button>
          <!-- add-modal -->
          <div class="modal fade" id="add-modal" tabindex="-1">
            <div class="modal-dialog modal-lg">
              <div class="modal-content">
                <div class="modal-header">
                  <h1 class="modal-title fs-5">增加</h1>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                  <form>
                    <div class="row mb-3">
                      <label for="name" class="col-2 col-form-label">name:</label>
                      <div class="col-10">
                        <input v-model="studentAdd.name" type="text" class="form-control" id="name">
                      </div>
                    </div>
                  </form>
                </div>
                <div class="modal-footer">
                  <button @click="add" type="button" class="btn btn-primary">确认</button>
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
                </div>
              </div>
            </div>
          </div>

        </div>
      </div>
    </form>

    <table class="table">
      <thead>
        <tr>
          <th>id</th>
          <th>name</th>
          <th>option</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="student in students" :key="student.id">
          <td>{{ student.id }}</td>
          <td>{{ student.name }}</td>
          <td>
            <button @click="remove(student.id)" type="button" class="btn btn-primary btn-sm me-2">删除</button>
            <button type="button" class="btn btn-primary btn-sm" data-bs-toggle="modal"
              :data-bs-target="'#update-modal-' + student.id">修改</button>
            <!-- update-modal -->
            <div class="modal fade" :id="'update-modal-' + student.id" tabindex="-1">
              <div class="modal-dialog modal-lg">
                <div class="modal-content">
                  <div class="modal-header">
                    <h1 class="modal-title fs-5">修改</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body">
                    <form>
                      <div class="row mb-3">
                        <label for="name" class="col-2 col-form-label">name:</label>
                        <div class="col-10">
                          <input v-model="student.name" type="text" class="form-control" id="name">
                        </div>
                      </div>
                    </form>
                  </div>
                  <div class="modal-footer">
                    <button @click="update(student)" type="button" class="btn btn-primary">确认</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
                  </div>
                </div>
              </div>
            </div>

          </td>
        </tr>
      </tbody>
    </table>

    <nav aria-label="Page navigation example">
      <ul class="pagination justify-content-end">
        <li class="page-item" @click="clickPage(-2)">
          <a class="page-link" href="#">前一页</a>
        </li>
        <li :class="'page-item ' + page.isActive" v-for="page in pages" :key="page.number"
          @click="clickPage(page.number)">
          <a class="page-link" href="#">{{ page.number }}</a>
        </li>
        <li class="page-item" @click="clickPage(-1)">
          <a class="page-link" href="#">后一页</a>
        </li>
      </ul>
    </nav>
  </CardBase>
</template>

<script>
import CardBase from '../components/CardBase.vue';
import $ from 'jquery';
import { useStore } from 'vuex';
import { ref, reactive } from 'vue';
import { Modal } from 'bootstrap/dist/js/bootstrap';

export default {
  name: 'ChangeView',
  components: {
    CardBase,
  },
  setup() {
    const store = useStore();
    let students = ref([]);
    let studentAdd = reactive({
      name: '',
    });
    let pages = ref([]);
    let recordsCount = 0;
    let pageSize = 10;
    let currentPage = 1;
    let findContent = reactive({
      name: '',
    })

    const clickPage = page => {
      if (page === -2) page = currentPage - 1;
      else if (page === -1) page = currentPage + 1;
      let maxPage = parseInt(Math.ceil(recordsCount / pageSize));
      if (page >= 1 && page <= maxPage) {
        pullPage(page);
      }
    };

    const updatePage = () => {
      let maxPage = parseInt(Math.ceil(recordsCount / pageSize));
      let newPages = [];
      for (let i = currentPage - 2; i <= currentPage + 2; i++) {
        if (i >= 1 && i <= maxPage) {
          newPages.push({
            number: i,
            isActive: i === currentPage ? 'active' : ''
          });
        }
      }
      pages.value = newPages;
    };

    const pullPage = (page) => {
      currentPage = page
      $.ajax({
        url: 'http://127.0.0.1:3000/student/get/',
        type: 'get',
        data: {
          page: page,
          size: pageSize,
          ...findContent,
        },
        headers: {
          Authorization: 'Bearer ' + store.state.user.token
        },
        success(resp) {
          students.value = resp.students;
          recordsCount = resp.recordsCount;
          updatePage();
        }
      });
    };

    pullPage(currentPage);

    const add = () => {
      $.ajax({
        url: 'http://127.0.0.1:3000/student/add/',
        type: 'post',
        data: {
          name: studentAdd.name,
        },
        headers: {
          Authorization: 'Bearer ' + store.state.user.token
        },
        success(resp) {
          if (resp.errorMessage === 'success') {
            Modal.getInstance("#add-modal").hide();
            pullPage(currentPage);
          }
        }
      });
    };

    const remove = (id) => {
      $.ajax({
        url: 'http://127.0.0.1:3000/student/remove/',
        type: 'post',
        data: {
          id: id
        },
        headers: {
          Authorization: 'Bearer ' + store.state.user.token
        },
        success(resp) {
          if (resp.errorMessage === 'success') {
            pullPage(currentPage);
          }
        }
      });
    };

    const update = (student) => {
      $.ajax({
        url: 'http://127.0.0.1:3000/student/update/',
        type: 'post',
        data: {
          id: student.id,
          name: student.name,
        },
        headers: {
          Authorization: 'Bearer ' + store.state.user.token
        },
        success(resp) {
          if (resp.errorMessage === 'success') {
            Modal.getInstance('#update-modal-' + student.id).hide();
            pullPage(currentPage);
          }
        }
      });
    };

    return {
      students,
      studentAdd,
      pages,
      findContent,
      clickPage,
      add,
      remove,
      update,
      pullPage,
    }
  }
}
</script>

<style scoped>
input {
  margin-bottom: 10px;
}
</style>
