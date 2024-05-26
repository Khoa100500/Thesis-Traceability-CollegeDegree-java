import * as Yup from "yup";
import { useAuth } from "../../context/useAuth";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";

type LoginFormsInputs = {
    username: string;
    password: string;
};

const validation = Yup.object().shape({
    username: Yup.string().required("Username is required"),
    password: Yup.string().required("Password is required"),
});

export default function LoginPage() {
    const { loginUser } = useAuth();
    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm<LoginFormsInputs>({ resolver: yupResolver(validation) });

    const handleLogin = (form: LoginFormsInputs) => {
        loginUser(form.username, form.password);
    };
    return (
        <div
            className="d-flex justify-content-center align-items-center vh-100"
            style={{ backgroundColor: "#f0f2f5" }}
        >
            <div className="w-100" style={{ maxWidth: "400px" }}>
                <div
                    className="card shadow-sm p-4"
                    style={{ borderRadius: "10px", backgroundColor: "#ffffff" }}
                >
                    <h2 className="text-center mb-4">Login</h2>
                    <form onSubmit={handleSubmit(handleLogin)}>
                        <div className="mb-3">
                            <label htmlFor="usernname" className="form-label">
                                Username
                            </label>
                            <input
                                type="text"
                                className="form-control"
                                id="username"
                                placeholder="Enter username"
                                {...register("username")}
                                required
                            />
                            {errors.username ? (
                                <p>{errors.username.message}</p>
                            ) : (
                                ""
                            )}
                        </div>
                        <div className="mb-3">
                            <label htmlFor="password" className="form-label">
                                Password
                            </label>
                            <input
                                type="password"
                                className="form-control"
                                id="password"
                                placeholder="Password"
                                {...register("password")}
                                required
                            />
                            {errors.password ? (
                                <p>{errors.password.message}</p>
                            ) : (
                                ""
                            )}
                        </div>
                        <div className="mb-3 form-check">
                            <input
                                type="checkbox"
                                className="form-check-input"
                                id="rememberMe"
                                // checked={rememberMe}
                                // onChange={(e) =>
                                //     setRememberMe(e.target.checked)
                                // }
                            />
                            <label
                                className="form-check-label"
                                htmlFor="rememberMe"
                            >
                                Remember me
                            </label>
                        </div>
                        <button
                            type="submit"
                            className="btn btn-primary w-100 mt-4"
                        >
                            Login
                        </button>
                    </form>
                    <div className="text-center mt-3">
                        <a
                            href="/forgot-password"
                            className="text-decoration-none"
                        >
                            Forgot password?
                        </a>
                    </div>
                    <div className="text-center mt-3">
                        <span>Don't have an account yet? </span>
                        <a href="/register" className="text-decoration-none">
                            Register
                        </a>
                    </div>
                </div>
            </div>
        </div>
    );
}
