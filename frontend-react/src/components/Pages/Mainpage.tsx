import { SetStateAction, useState } from "react";
import Sidebar from "../Sidebar";
import { Outlet, useOutletContext } from "react-router-dom";

type ContextType = {
    toggle: boolean | null;
    setToggle: React.Dispatch<SetStateAction<boolean>>;
};

export default function Mainpage() {
    const [toggle, setToggle] = useState<boolean>(true);

    return (
        <div className="container-fluid bg-white min-vh-100">
            <div className="row">
                {toggle && (
                    <div className="col-4 col-md-2 vh-100 text-truncate position-fixed shadow p-3 mb-5 bg-body">
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
