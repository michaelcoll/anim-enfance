import Vue from 'vue';
import Router from 'vue-router';
import Structure from './views/Structures.vue';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/structure',
      name: 'structure',
      component: Structure,
    },
  ],
});
