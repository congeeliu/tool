<template>
  <nav class="navbar navbar-expand-lg" style="background-color: #e3f2fd;">
    <div class="container">
      <router-link class="navbar-brand" :to="{ name: 'home' }">Tool</router-link>
      <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <router-link class="nav-link active" :to="{ name: 'generate' }">生成</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" :to="{ name: 'change' }">转换</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" :to="{ name: 'test' }">测试</router-link>
          </li>
        </ul>
        <ul class="navbar-nav" v-if="$store.state.user.isLogin">
          <li class="nav-item">
            <div class="nav-link">{{ $store.state.user.username }}</div>
          </li>
          <li class="nav-item">
            <a @click="logout" class="nav-link">退出</a>
          </li>
        </ul>
        <ul class="navbar-nav" v-else>
          <li class="nav-item">
            <router-link class="nav-link" :to="{ name: 'login' }">登录</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" :to="{ name: 'register' }">注册</router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
import { useStore } from 'vuex';

export default {
  name: 'NavBar',
  setup() {
    const store = useStore();

    const logout = () => {
      store.dispatch("logout");
    }

    return {
      logout
    }
  }
}
</script>