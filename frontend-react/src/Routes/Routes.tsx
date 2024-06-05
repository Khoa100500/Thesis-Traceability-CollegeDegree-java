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
import GuestPage from "../components/Pages/GuestPage";
import StudentReport from "../components/StudentReport";

export const router = createBrowserRouter([
    {
        path: "/",
        element: <App />,
        children: [
            { path: "", element: <WelcomPage /> },
            {
                path: "guest",
                element: <GuestPage />,
            },
            { path: "report/:reportID", element: <StudentReport /> },
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
                    { path: "report/:reportID", element: <Report /> },
                ],
            },
        ],
    },
    {
        path: "*",
        element: <Notfound />,
    },
]);
