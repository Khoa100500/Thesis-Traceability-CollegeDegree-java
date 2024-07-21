import { createContext, useEffect, useState } from "react";
import { UserProfile } from "../models/User";
import { useNavigate } from "react-router-dom";
import { loginAPI, registerAPI } from "../services/AuthService";
import { toast } from "react-toastify";
import React from "react";
import instance from "../services/axios-instance";
import { QueryClient } from "@tanstack/react-query";
import { queryClient } from "../main";

type UserContextType = {
    user: UserProfile | null;
    token: string | null;
    registerUser: (
        email: string,
        username: string,
        password: string,
        fullname: string
    ) => void;
    loginUser: (username: string, password: string) => void;
    logout: () => void;
    isLoggedIn: () => boolean;
};

type Props = { children: React.ReactNode };

const UserContext = createContext<UserContextType>({} as UserContextType);

export const UserProvider = ({ children }: Props) => {
    const navigate = useNavigate();
    const [token, setToken] = useState<string | null>(null);
    const [user, setUser] = useState<UserProfile | null>(null);
    const [isReady, setIsReady] = useState(false);

    useEffect(() => {
        const user = localStorage.getItem("user");
        const token = localStorage.getItem("token");
        if (user && token) {
            setUser(JSON.parse(user));
            setToken(token);
            instance.defaults.headers.common["Authorization"] =
                "Bearer " + token;
        }
        setIsReady(true);
    }, []);

    const registerUser = async (
        email: string,
        username: string,
        password: string,
        fullname: string
    ) => {
        await registerAPI(email, username, password, fullname)
            .then((res) => {
                if (res) {
                    localStorage.setItem("token", res?.data.token);
                    const userObj = {
                        userID: res?.data.userID,
                        username: res?.data.username,
                        email: res?.data.email,
                    };
                    localStorage.setItem("user", JSON.stringify(userObj));
                    setToken(res?.data.token);
                    setUser(userObj!);
                    toast.success("Login success");
                    navigate("/home");
                }
            })
            .catch((e) => toast.warning(e));
    };

    const loginUser = async (username: string, password: string) => {
        localStorage.clear();
        await loginAPI(username, password)
            .then((res) => {
                if (res) {
                    localStorage.setItem("token", res?.data.token);
                    const userObj = {
                        userID: res?.data.userID,
                        username: res?.data.username,
                        email: res?.data.email,
                    };
                    localStorage.setItem("user", JSON.stringify(userObj));
                    setToken(res?.data.token);
                    setUser(userObj!);
                    toast.success("Login success");
                    navigate("/home");
                }
            })
            .catch((e) => toast.warning(e));
    };

    const isLoggedIn = () => {
        return !!user;
    };

    const logout = () => {
        localStorage.removeItem("token");
        localStorage.removeItem("user");
        setUser(null);
        setToken("");
        queryClient.removeQueries();
        navigate("/login");
    };

    return (
        <UserContext.Provider
            value={{ loginUser, user, token, logout, isLoggedIn, registerUser }}
        >
            {isReady ? children : null}
        </UserContext.Provider>
    );
};

export const useAuth = () => React.useContext(UserContext);
