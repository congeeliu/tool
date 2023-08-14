import $ from 'jquery';

export default {
  state: {
    id: '',
    username: '',
    token: '',
    isLogin: ''
  },
  mutations: {
    updateToken(state, token) {
      state.token = token;
    },

    updateUser(state, user) {
      state.id = user.id;
      state.username = user.username;
      state.isLogin = user.isLogin;
    },

    logout(state) {
      state.id = '';
      state.username = '';
      state.token = '';
      state.isLogin = false;
    }
  },
  actions: {
    login(context, data) {
      $.ajax({
        url: 'http://127.0.0.1:3000/user/login/',
        type: 'post',
        data: {
          username: data.username,
          password: data.password,
        },
        success(resp) {
          // console.log(resp);
          if (resp.errorMessage === 'success') {
            localStorage.setItem('jwtToken', resp.token);
            context.commit('updateToken', resp.token);
            // context.commit('updateUser', {
            //   id: resp.id,
            //   username: resp.username,
            //   isLogin: true
            // });
            data.success(resp);
          } else {
            data.error(resp);
          }
        },
        error(resp) {
          data.error(resp);
        }
      });
    },

    getInfo(context, data) {
      $.ajax({
        url: 'http://127.0.0.1:3000/user/info/',
        type: 'get',
        headers: {
          Authorization: 'Bearer ' + context.state.token,
        },
        success(resp) {
          // console.log(resp);
          if (resp.errorMessage === 'success') {
            context.commit('updateUser', {
              ...resp,
              isLogin: true
            });
            data.success(resp);
          } else {
            data.error(resp);
          }
        },
        error(resp) {
          data.error(resp);
        }
      });
    },

    logout(context) {
      localStorage.removeItem('jwtToken');
      context.commit('logout');
    }
  },
  modules: {
  }
};