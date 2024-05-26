import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";

function WelcomPage() {
    const navigate = useNavigate();

    const handleLogin = () => {
        navigate("/login");
    };

    const handleSignUp = () => {
        navigate("/register");
    };

    return (
        <div className="vh-100 d-flex flex-column">
            {/* Navigation Bar */}
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <div className="container-fluid">
                    <Link to="/home" className="navbar-brand">
                        MyApp
                    </Link>
                    <div className="collapse navbar-collapse">
                        <ul className="navbar-nav ms-auto mb-2 mb-lg-0">
                            <li className="nav-item">
                                <button
                                    className="btn btn-link nav-link"
                                    onClick={handleLogin}
                                >
                                    Login
                                </button>
                            </li>
                            <li className="nav-item">
                                <button
                                    className="btn btn-link nav-link"
                                    onClick={handleSignUp}
                                >
                                    Sign Up
                                </button>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>

            {/* Main Content */}
            <div className="container d-flex flex-column justify-content-center align-items-center flex-grow-1">
                <h1>Welcome to MyApp!</h1>
                <p className="lead text-center mb-4">
                    Your one-stop solution for managing your classes and scores.
                </p>
                <div>
                    <button
                        className="btn btn-primary me-3"
                        onClick={handleLogin}
                    >
                        Login
                    </button>
                    <button
                        className="btn btn-secondary"
                        onClick={handleSignUp}
                    >
                        Sign Up
                    </button>
                </div>
            </div>
        </div>
    );
}

export default WelcomPage;
