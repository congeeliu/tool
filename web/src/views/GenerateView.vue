<template>
  <CardBase>
    <form>
      <div class="row mb-3">
        <label for="package-name" class="col-sm-1 col-form-label">包名</label>
        <div class="col-sm-11">
          <input v-model="packageName" type="text" class="form-control" id="package-name">
        </div>
      </div>

      <div class="row mb-3">
        <label for="class-name" class="col-sm-1 col-form-label">类名</label>
        <div class="col-sm-11">
          <input v-model="className" type="text" class="form-control" id="class-name">
        </div>
      </div>

      <div v-for="i in properties.length" :key="i">
        <div class="row mb-3">
          <label for="attribute" class="col-1 col-form-label">属性{{ i }}</label>
          <div class="col-5">
            <input v-model="properties[i - 1].name" type="text" class="form-control" id="attribute">
          </div>
          <label for="type" class="col-1 col-form-label">类型</label>
          <div class="col-3">
            <select v-model="properties[i - 1].type" class="form-select">
              <option value="Integer">Integer</option>
              <option value="String">String</option>
              <option value="Date">Date</option>
            </select>
          </div>
          <div class="col-2">
            <button v-if="i === properties.length" @click="add" type="button" class="btn btn-primary m-2">增加</button>
            <button v-if="i === properties.length" @click="del" type="button" class="btn btn-danger m-2">删除</button>
          </div>
        </div>
      </div>
    </form>
    <div style="color: red;">{{ errorMessage }}</div>
    <button @click="submit" type="submit" class="btn btn-primary m-2">提交</button>

  </CardBase>
</template>

<script>
import CardBase from '../components/CardBase.vue';
import $ from 'jquery';

export default {
  name: 'GenerateView',
  components: {
    CardBase,
  },
  data() {
    return {
      // cnt: 1,
      // a: [1],
      packageName: '',
      className: '',
      properties: [{ name: '', type: '' }],
      // entity: {
      //   attribute: [],
      //   type: []
      // },
      errorMessage: '',
    }
  },
  methods: {
    add() {
      // this.a.push(this.a.length + 1);
      // this.cnt++;
      this.properties.push({ name: '', type: '' });
    },
    del() {
      // this.a.splice(this.a.length - 1, 1);
      // this.cnt--;
      this.properties.pop();
    },
    submit() {
      if (this.properties.length === 0) {
        // console.log("error");
        this.errorMessage = '属性至少要有1项';
        return;
      }
      for (const property of this.properties) {
        if (property.name === '' || property.type === '') {
          this.errorMessage = '属性名或属性类型不能为空';
          return;
        }
      }
      $.ajax({
        url: 'http://127.0.0.1:3000/generate/',
        type: 'post',
        // dataType: "json",
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify({
          packageName: this.packageName,
          className: this.className,
          properties: this.properties,
          // attribute: JSON.stringify(this.attribute),
          // type: JSON.stringify(this.type)
        }),
        // data: JSON.stringify(this.properties),
        headers: {
          Authorization: 'Bearer ' + this.$store.state.user.token,
        },
        success() {
          console.log("success");
        },
        error(resp) {
          console.log(resp);
        }
      });
    }
  },
}
</script>


