<template>
  <div>
    <v-content>
      <v-container
        fluid
        grid-list-md>

        <v-alert :value="structures.length === 0 && !error" color="info" icon="info">
          Aucune structure trouv&eacute;e.
        </v-alert>

        <v-alert :value="error" color="error" icon="error">
          Erreur lors de la r&eacute;cup&eacute;ration des structures.
        </v-alert>

        <v-layout row wrap>
          <v-flex
            xs12 sm6 md3
            v-for="structure in structures"
            :key="structure.id">
            <v-card>
              <v-card-media
                :src="structure.imageSrc"
                height="200px">
                <v-container
                  fill-height
                  fluid
                  pa-2>
                  <v-layout fill-height>
                    <v-flex xs12 align-end flexbox>
                      <span class="headline white--text" v-text="structure.name"></span>
                    </v-flex>
                  </v-layout>
                </v-container>
              </v-card-media>

              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn icon @click="editItem(structure.id)">
                  <v-icon>edit</v-icon>
                </v-btn>
                <v-btn icon @click="deleteItem(structure.id)">
                  <v-icon>delete</v-icon>
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>

    </v-content>
    <CreateStructure v-on:on-save="onStructureCreate"/>
  </div>
</template>

<style lang="scss">
  .v-card__media__background {
    filter: brightness(75%);
  }
</style>

<script>
/* eslint-disable no-param-reassign */

import { deleteStructure, list } from '../services/structureService';
import CreateStructure from '../components/structure/CreateStructure.vue';

export function listStructure(vm) {
  list()
    .then((response) => {
      vm.structures = response.data;
      vm.error = false;
    })
    .catch(() => {
      vm.error = true;
    });
}

export default {
  components: { CreateStructure },
  data: () => ({
    error: false,
    structures: [],
  }),
  mounted() {
    listStructure(this);
  },
  methods: {
    onStructureCreate() {
      listStructure(this);
    },
    deleteItem(structureId) {
      deleteStructure(structureId)
        .then(() => {
          listStructure(this);
        });
    },
  },
};
</script>
