import axios from "axios";
import { handleError } from "../helpers/ErrorHandler";
import { UserProfileToken } from "../models/User";
import instance from "./axios-instance";
import { toast } from "react-toastify";

export const loginAPI = async (username: string, password: string) => {
    try {
        const data = await instance.post<UserProfileToken>(
            "/auth/authenticate",
            {
                username: username,
                password: password,
            }
        );
        return data;
    } catch (error) {
        if (axios.isAxiosError(error)) {
            toast.error(error.response?.data.message);
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
        const data = await instance.post<UserProfileToken>("/auth/register", {
            email: email,
            fullname: fullname,
            username: username,
            password: password,
        });
        return data;
    } catch (error) {
        if (axios.isAxiosError(error)) {
            handleError(error);
        }
    }
};
