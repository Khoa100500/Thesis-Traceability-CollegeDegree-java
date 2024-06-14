import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const GuestPage = () => {
    const [studentId, setStudentId] = useState<string>("");
    const navigate = useNavigate();

    const handleSearch = async (e: React.FormEvent) => {
        e.preventDefault();
        navigate(`/records/${studentId}`);
    };

    return (
        <div className="container mt-5 ">
            <div className="row justify-content-center">
                <div className="col-md-8">
                    <h1 className="text-center mb-4">Search Student Scores</h1>
                    <form onSubmit={handleSearch}>
                        <div className="mb-3">
                            <label htmlFor="studentId" className="form-label">
                                Student ID
                            </label>
                            <input
                                type="text"
                                className="form-control"
                                id="studentId"
                                placeholder="Enter Student ID"
                                value={studentId}
                                onChange={(e) => setStudentId(e.target.value)}
                                required
                            />
                        </div>
                        <button type="submit" className="btn btn-primary">
                            Search
                        </button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default GuestPage;
