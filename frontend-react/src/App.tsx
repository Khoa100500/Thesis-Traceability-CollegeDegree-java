import { ToastContainer } from "react-toastify";
import "react-toastify/ReactToastify.css";
import { UserProvider } from "./context/useAuth";
import { Outlet } from "react-router-dom";

function App() {
    return (
        <>
            <UserProvider>
                {/* <Outlet /> */}
                <Outlet />
                <ToastContainer />
            </UserProvider>
        </>
    );
}

export default App;
