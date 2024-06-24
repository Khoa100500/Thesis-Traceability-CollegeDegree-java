import { Link } from "react-router-dom";
import { useAuth } from "../context/useAuth";

export default function Sidebar() {
    const { logout } = useAuth();

    return (
        <div className="bg-white p-2 ">
            <div className="m-2">
                <Link to="/home" style={{ textDecoration: "none" }}>
                    <i className="bi bi-bootstrap-fill me-3 fs-4"></i>
                    <span className="brand-name fs-4">EduTrace</span>
                </Link>
            </div>
            <hr className="text-dark" />
            <div className="div list-group list-group-flush">
                <a className="list-group-item py-2">
                    <i className="bi bi-clipboard-data fs-5 me-3"></i>
                    <span>Report</span>
                </a>
                <a className="list-group-item py-2">
                    <i className="bi bi-people fs-5 me-3"></i>
                    <span>Students</span>
                </a>
                <a className="list-group-item py-2" onClick={logout}>
                    <i className="bi bi-power fs-5 me-3"></i>
                    <span>Logout</span>
                </a>
            </div>
        </div>
    );
}
