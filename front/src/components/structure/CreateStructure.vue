<template>
  <div>
    <v-dialog v-model="showDialog" persistent max-width="400px">
      <v-form ref="form" lazy-validation :onsubmit="submit">
        <v-card>
          <v-progress-linear indeterminate v-if="sending"></v-progress-linear>
          <v-card-title>
            <span class="headline">Nouvelle structure</span>
          </v-card-title>
          <v-card-text>
            <v-container grid-list-md>
              <v-layout wrap>
                <v-text-field
                  v-model="name"
                  :rules="nameRules"
                  label="Nom"
                  required
                ></v-text-field>
                <v-text-field
                  v-model="imageSrc"
                  :rules="nameRules"
                  label="Image Url"
                  required
                ></v-text-field>
              </v-layout>
            </v-container>
          </v-card-text>

          <v-alert :value="sendError" type="error">
            Erreur lors de la cr&eacute;ation de la structure.
          </v-alert>

          <v-divider></v-divider>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn flat @click="onCloseDialog()">Fermer</v-btn>
            <v-btn color="primary" type="submit" @click="submit" :disabled="sending">Ajouter</v-btn>
          </v-card-actions>
        </v-card>
      </v-form>
    </v-dialog>

    <v-btn fab bottom right fixed color="accent" @click.stop="showDialog = true">
      <v-icon>add</v-icon>
    </v-btn>

    <v-snackbar v-model="structureSaved" bottom>
      La structure &nbsp; <strong>{{ lastStructure }}</strong> &nbsp; a été créée avec succès !
    </v-snackbar>

  </div>
</template>

<script>
import { save } from '../../services/structureService';

export default {
  data: () => ({
    showDialog: false,
    name: '',
    nameRules: [
      v => !!v || 'Un nom est requis',
      v => (v && v.length >= 3) || 'Nom invalide',
    ],
    imageSrc: '',
    srcRules: [
      v => !!v || 'Une url est requise',
      v => (v && v.length >= 3) || 'url invalide',
    ],
    structureSaved: false,
    sending: false,
    lastStructure: null,
    sendError: false,
  }),
  methods: {
    submit() {
      if (this.$refs.form.validate()) {
        this.sending = true;
        this.lastStructure = this.name;

        const vm = this;

        save({ name: this.name, imageSrc: this.imageSrc })
          .then((response) => {
            vm.onSave(response.data);
          })
          .catch(() => {
            vm.sendError = true;
          })
          .finally(() => {
            vm.sending = false;
          });
      }
    },
    clear() {
      this.$refs.form.reset();
    },
    onSave(structure) {
      this.structureSaved = true;
      this.clear();
      this.$emit('on-save', structure);
    },
    onCloseDialog() {
      this.showDialog = false;
      this.$emit('on-close');
    },
  },
};
</script>

<style lang="scss" scoped>
</style>
