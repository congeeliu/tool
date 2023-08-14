import { createRouter, createWebHistory } from 'vue-router';
import GenerateView from '../views/GenerateView.vue';
import ChangeView from '../views/ChangeView.vue';
import StudentView from '../views/StudentView.vue';
import LoginView from '../views/LoginView.vue';
import RegisterView from '../views/RegisterView.vue';
import NotFound from '../views/NotFound.vue';
import store from '../store/index';

const routes = [
  {
    path: '/',
    name: 'home',
    redirect: '/generate/',
    meta: {
      requestAuth: true,
    }
  },
  {
    path: '/generate/',
    name: 'generate',
    component: GenerateView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: '/change/',
    name: 'change',
    component: ChangeView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: '/test/',
    name: 'test',
    component: StudentView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: '/login/',
    name: 'login',
    component: LoginView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: '/register/',
    name: 'register',
    component: RegisterView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: '/404/',
    name: '404',
    component: NotFound,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: '/:catchAll(.*)',
    redirect: '/404/'
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  let flag = true;
  const jwtToken = localStorage.getItem("jwtToken");
  if (jwtToken) {
    store.commit("updateToken", jwtToken);
    store.dispatch("getInfo", {
      success() {
      },
      error() {
        next({ name: "login" });
      }
    })
  } else {
    flag = false;
  }

  if (to.meta.requestAuth && !store.state.user.isLogin) {
    if (flag) {
      next();
    } else {
      next({ name: "login" });
    }
  } else {
    next();
  }
})

export default router
