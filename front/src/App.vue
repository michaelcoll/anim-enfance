<template>
  <v-app>
    <v-navigation-drawer
      permanent
      width="200"
      :mini-variant="miniVariant"
      enable-resize-watcher
      clipped
      app
      dark
    >
      <v-list dense>
        <v-list-tile
          v-for="item in items"
          :key="item.title"
          :to="item.action"
          ripple>
          <v-list-tile-action>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-tile-action>

          <v-list-tile-content>
            <v-list-tile-title>{{ item.title }}</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
      </v-list>
    </v-navigation-drawer>
    <v-toolbar app fixed clipped-left color="primary" dark>
      <v-toolbar-side-icon @click.stop="miniVariant = !miniVariant"></v-toolbar-side-icon>
      <v-toolbar-title>AnimEnfance</v-toolbar-title>

      <v-spacer></v-spacer>

      <login />
    </v-toolbar>

    <router-view/>
  </v-app>
</template>

<style lang="scss" scoped>
</style>

<script>
import Login from './components/login/Login.vue';

export default {
  components: { Login },
  data() {
    return {
      items: [
        { title: 'Crèche', icon: 'child_care', action: '/structure' },
        { title: 'Partenaires', icon: 'people', action: '/partner' },
        { title: 'Réservation', icon: 'event', action: '/events' },
      ],
      clipped: false,
      drawer: true,
      fixed: false,
      miniVariant: true,
    };
  },
};
</script>

