<template>
  <CardBase>
    <form>
      <div class="row mb-3">
        <label for="package-name" class="col-sm-1 col-form-label">包名</label>
        <div class="col-sm-11">
          <input v-model="entity.packageName" type="text" class="form-control" id="package-name">
        </div>
      </div>

      <div class="row mb-3">
        <label for="entity-name" class="col-sm-1 col-form-label">实体名</label>
        <div class="col-sm-11">
          <input v-model="entity.entityName" type="text" class="form-control" id="entity-name">
        </div>
      </div>

      <div v-for="i in this.a" :key="i">
        <div class="row mb-3">
          <label for="attribute" class="col-1 col-form-label">属性{{ i }}</label>
          <div class="col-5">
            <input v-model="entity.attribute[i - 1]" type="text" class="form-control" id="attribute">
          </div>
          <label for="type" class="col-1 col-form-label">类型</label>
          <div class="col-3">
            <select v-model="entity.type[i - 1]" class="form-select">
              <option value="Integer">Integer</option>
              <option value="String">String</option>
              <option value="Date">Date</option>
            </select>
          </div>
          <div class="col-2">
            <button v-if="i === a.length" @click="add" type="button" class="btn btn-primary m-2">增加</button>
            <button v-if="i === a.length" @click="del" type="button" class="btn btn-danger m-2">删除</button>
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
      a: [1],
      entity: {
        packageName: '',
        entityName: '',
        attribute: [],
        type: []
      },
      errorMessage: '',
    }
  },
  methods: {
    add() {
      this.a.push(this.a.length + 1);
    },
    del() {
      this.a.splice(this.a.length - 1, 1);
    },
    submit() {
      if (this.entity.attribute.length === 0) {
        // console.log("error");
        this.errorMessage = '属性至少要有1项';
        return;
      }
      this.errorMessage = '';
      $.ajax({
        url: 'http://127.0.0.1:3000/generate/',
        type: 'post',
        data: {
          packageName: this.entity.packageName,
          entityName: this.entity.entityName,
          attribute: JSON.stringify(this.entity.attribute),
          type: JSON.stringify(this.entity.type)
        },
        headers: {
          Authorization: 'Bearer ' + this.$store.state.user.token,
        },
        success() {
          console.log("success");
        }
      });
    }
  },
}
</script>


