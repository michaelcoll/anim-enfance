import axios from 'axios';

const baseUrl = 'http://192.168.43.82:8082/structure';

export function list() {
  return axios
    .get(baseUrl);
}

export function save(structure) {
  return axios
    .put(baseUrl, structure);
}

export function deleteStructure(structureId) {
  return axios
    .delete(baseUrl.concat('/').concat(structureId))
    .catch((e) => {
      console.error(e);
    });
}
