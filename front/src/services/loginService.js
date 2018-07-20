import axios from 'axios';

const baseUrl = 'http://192.168.43.82:8083/auth';
const instance = axios.create({
  baseURL: baseUrl,
  timeout: 1000,
});

export function login(username, password) {
  return instance
    .get('/user', {
      auth: {
        username,
        password,
      },
    })
    .then((response) => {
      console.log(response.headers['x-auth-token']);
    });
}

export function logout() {
  return instance.get('/logout');
}
