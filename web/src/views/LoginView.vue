<template>
  <CardBase>
    <div class="row justify-content-md-center">
      <div class="col-3">
        <form @submit.prevent="login">
          <div class="mb-3">
            <label for="username" class="form-label">用户名</label>
            <input v-model="username" type="text" class="form-control" id="username" placeholder="请输入用户名">
          </div>
          <div class="mb-3">
            <label for="password" class="form-label">密码</label>
            <input v-model="password" type="password" class="form-control" id="password" placeholder="请输入密码">
          </div>
          <div class="error-message">{{ errorMessage }}</div>
          <button type="submit" class="btn btn-primary" style="width: 100%;">登录</button>
        </form>
      </div>
    </div>
  </CardBase>
</template>

<script>
import CardBase from '../components/CardBase.vue';
import { ref } from 'vue';
import { useStore } from 'vuex';
import router from '@/router/index.js';

export default {
  name: 'LoginView',
  components: {
    CardBase
  },
  setup() {
    const store = useStore();
    let username = ref('');
    let password = ref('');
    let errorMessage = ref('');

    const login = () => {
      errorMessage.value = '';
      store.dispatch('login', {
        username: username.value,
        password: password.value,
        success() {
          store.dispatch('getInfo', {
            success() {
              router.push({ name: 'generate' });
            }
          });
          router.push({ name: 'generate' });
        },
        error(resp) {
          errorMessage.value = resp.errorMessage;
        }
      });
    };

    return {
      username,
      password,
      errorMessage,
      login
    }
  }
}
</script>

<style>
.error-message {
  color: red;
}
</style>