import axios from "axios";

export const signInApi = async (data) => {
    const response = await axios.post('http://localhost:4000/api/auth/signin', data).catch((error) => null);
    if(!response) return null;

    const result = response.data;
    return result;
};

export const signUpApi = async (data) => {
    const response = await axios.post('http://localhost:4000/api/auth/signup', data).catch((error) => null);
    if(!response) return null;

    const result = response.data;
    return result;
};