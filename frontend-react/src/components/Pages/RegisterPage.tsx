import * as Yup from "yup";
import { useAuth } from "../../context/useAuth";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";

type RegisterFormInputs = {
    username: string;
    password: string;
    email: string;
    fullname: string;
};

const validation = Yup.object().shape({
    username: Yup.string().required("Username is required"),
    password: Yup.string().required("Password is required"),
    email: Yup.string().required("Email is required"),
    fullname: Yup.string().required("Fullname is required"),
});

const RegisterPage = () => {
    const { registerUser } = useAuth();
    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm<RegisterFormInputs>({
        resolver: yupResolver(validation),
    });

    const handleRegister = (form: RegisterFormInputs) => {
        registerUser(form.email, form.username, form.password, form.fullname);
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
                    <h2 className="text-center mb-4">Register</h2>
                    <form onSubmit={handleSubmit(handleRegister)}>
                        <div className="mb-3">
                            <label htmlFor="username" className="form-label">
                                Username
                            </label>
                            <input
                                type="text"
                                className="form-control"
                                id="username"
                                placeholder="Enter username"
                                {...register("username")}
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
                                placeholder="Enter password"
                                {...register("password")}
                            />
                            {errors.password ? (
                                <p>{errors.password.message}</p>
                            ) : (
                                ""
                            )}
                        </div>
                        <div className="mb-3">
                            <label htmlFor="fullName" className="form-label">
                                Full Name
                            </label>
                            <input
                                type="text"
                                className="form-control"
                                id="fullName"
                                placeholder="Enter full name"
                                {...register("fullname")}
                            />
                            {errors.fullname ? (
                                <p>{errors.fullname.message}</p>
                            ) : (
                                ""
                            )}
                        </div>
                        <div className="mb-3">
                            <label htmlFor="email" className="form-label">
                                Email address
                            </label>
                            <input
                                type="email"
                                className="form-control"
                                id="email"
                                placeholder="Enter email"
                                {...register("email")}
                            />
                            {errors.email ? <p>{errors.email.message}</p> : ""}
                        </div>
                        <button
                            type="submit"
                            className="btn btn-primary w-100 mt-4"
                        >
                            Register
                        </button>
                    </form>
                    <div className="text-center mt-3">
                        <span>Already have an account? </span>
                        <a href="/login" className="text-decoration-none">
                            Login
                        </a>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default RegisterPage;
