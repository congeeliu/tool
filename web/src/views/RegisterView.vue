<template>
  <CardBase>
    <div class="row justify-content-md-center">
      <div class="col-3">
        <form @submit.prevent="register">
          <div class="mb-3">
            <label for="username" class="form-label">用户名</label>
            <input v-model="username" type="text" class="form-control" id="username" placeholder="请输入用户名">
          </div>
          <div class="mb-3">
            <label for="password" class="form-label">密码</label>
            <input v-model="password" type="password" class="form-control" id="password" placeholder="请输入密码">
          </div>
          <div class="mb-3">
            <label for="password-confirm" class="form-label">确认密码</label>
            <input v-model="passwordConfirm" type="password" class="form-control" id="password-confirm"
              placeholder="请再次输入密码">
          </div>
          <div class="error-message">{{ errorMessage }}</div>
          <button type="submit" class="btn btn-primary" style="width: 100%;">注册</button>
        </form>
      </div>
    </div>
  </CardBase>
</template>

<script>
import CardBase from '../components/CardBase.vue';
import { ref } from 'vue';
import $ from 'jquery';
import router from '@/router/index.js';

export default {
  name: 'RegisterView',
  components: {
    CardBase
  },
  setup() {
    let username = ref('');
    let password = ref('');
    let passwordConfirm = ref('');
    let errorMessage = ref('');

    const register = () => {
      $.ajax({
        url: 'http://127.0.0.1:3000/user/register/',
        type: 'post',
        data: {
          username: username.value,
          password: password.value,
          passwordConfirm: passwordConfirm.value
        },
        success(resp) {
          if (resp.errorMessage === "success") {
            router.push({ name: 'generate' });
          } else {
            errorMessage.value = resp.errorMessage;
          }
        }
      });
    };

    return {
      username,
      password,
      passwordConfirm,
      errorMessage,
      register
    }
  }
}
</script>

<style>
.error-message {
  color: red;
}
</style>