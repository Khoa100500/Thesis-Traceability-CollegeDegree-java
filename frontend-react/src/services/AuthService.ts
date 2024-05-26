import axios from "axios";
import { handleError } from "../helpers/ErrorHandler";
import { UserProfileToken } from "../models/User";

const api = "api/v1";

export const loginAPI = async (username: string, password: string) => {
    try {
        const data = await axios.post<UserProfileToken>(
            api + "/auth/authenticate",
            {
                username: username,
                password: password,
            }
        );
        return data;
    } catch (error) {
        if (axios.isAxiosError(error)) {
            handleError(error);
        }
    }
};

export const registerAPI = async (
    email: string,
    username: string,
    password: string,
    fullname: string
) => {
    try {
        const data = await axios.post<UserProfileToken>(
            api + "/auth/register",
            {
                email: email,
                fullname: fullname,
                username: username,
                password: password,
            }
        );
        return data;
    } catch (error) {
        if (axios.isAxiosError(error)) {
            handleError(error);
        }
    }
};
