import { SetStateAction, useState } from "react";
// import Home from "./Home";
import Sidebar from "../Sidebar";
// import Report from "../Report";
import { Outlet, useOutletContext } from "react-router-dom";

type ContextType = {
    toggle: boolean | null;
    setToggle: React.Dispatch<SetStateAction<boolean>>;
};

export default function Mainpage() {
    const [toggle, setToggle] = useState<boolean>(true);

    return (
        <div className="container-fluid bg-secondary  min-vh-100">
            <div className="row">
                {toggle && (
                    <div className="col-4 col-md-2 bg-white vh-100 text-truncate position-fixed">
                        <Sidebar />
                    </div>
                )}
                {toggle && <div className="col-4 col-md-2"></div>}
                <div className="col">
                    <Outlet
                        context={{ toggle, setToggle } satisfies ContextType}
                    />
                </div>
            </div>
        </div>
    );
}

// eslint-disable-next-line react-refresh/only-export-components
export function useToggle() {
    return useOutletContext<ContextType>();
}
