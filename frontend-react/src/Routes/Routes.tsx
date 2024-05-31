import { createBrowserRouter } from "react-router-dom";
import LoginPage from "../components/Pages/LoginPage";
import Mainpage from "../components/Pages/Mainpage";
import App from "../App";
import Notfound from "../components/Pages/Notfound";
import RegisterPage from "../components/Pages/RegisterPage";
import ProtectedRoute from "./ProtectedRoute";
import Home from "../components/Pages/Home";
import Report from "../components/Report";
import WelcomPage from "../components/Pages/WelcomPage";

export const router = createBrowserRouter([
    {
        path: "/",
        element: <App />,
        children: [
            { path: "", element: <WelcomPage /> },
            { path: "login", element: <LoginPage /> },
            { path: "register", element: <RegisterPage /> },
            {
                path: "home",
                element: (
                    <ProtectedRoute>
                        <Mainpage />
                    </ProtectedRoute>
                ),
                children: [
                    { path: "", element: <Home /> },
                    { path: "report", element: <Report /> },
                ],
            },
        ],
    },
    {
        path: "*",
        element: <Notfound />,
    },
]);
