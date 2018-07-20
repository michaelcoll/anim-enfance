<template>
  <div>
    <v-dialog v-model="showLoginDialog" max-width="300px">
      <v-card>
        <v-progress-linear indeterminate v-if="sending"></v-progress-linear>
        <v-card-title>
          <span class="headline">Connexion</span>
        </v-card-title>
        <v-card-text>
          <v-container grid-list-md>
            <v-layout wrap>
              <v-form ref="form" lazy-validation :onsubmit="submit">
                <v-text-field
                  xs12
                  prepend-icon="perm_identity"
                  v-model="username"
                  :rules="[rules.required]"
                  label="Utilisateur"
                ></v-text-field>
                <v-text-field
                  :append-icon="showPassword ? 'visibility_off' : 'visibility'"
                  :rules="[rules.required, rules.min]"
                  :type="showPassword ? 'text' : 'password'"
                  xs12
                  prepend-icon="lock"
                  v-model="password"
                  label="Mot de passe"
                  @click:append="showPassword = !showPassword"
                ></v-text-field>
              </v-form>
            </v-layout>
          </v-container>
        </v-card-text>

        <v-alert :value="error" type="error">
          Erreur lors de la connexion.
        </v-alert>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" @click="submit">S'identifer</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-toolbar-items class="hidden-sm-and-down">
      <v-btn flat @click="showLoginDialog = true">Connexion</v-btn>
    </v-toolbar-items>

    <v-toolbar-items class="hidden-md-and-up">
      <v-menu bottom left>
        <v-btn slot="activator" dark icon>
          <v-icon>more_vert</v-icon>
        </v-btn>

        <v-list>
          <v-list-tile @click="showLoginDialog = true" ripple>
            <v-list-tile-title>Connexion</v-list-tile-title>
          </v-list-tile>
        </v-list>
      </v-menu>
    </v-toolbar-items>

  </div>
</template>

<script>
import { login, logout } from '../../services/loginService';

export default {
  data: () => ({
    showLoginDialog: false,
    error: false,
    username: '',
    password: '',
    showPassword: false,
    rules: {
      required: value => !!value || 'Requis.',
      min: v => v.length >= 8 || '8 charactères minimum',
    },
    sending: false,

  }),
  methods: {
    submit() {
      if (this.$refs.form.validate()) {
        this.sending = true;

        login(this.username, this.password)
          .then((response) => {
            this.error = false;
            this.sending = false;
            this.showLoginDialog = false;
          })
          .catch(() => {
            this.error = true;
            this.sending = false;
          });
      }
    },
  },
};
</script>

<style lang="scss" scoped>
</style>
