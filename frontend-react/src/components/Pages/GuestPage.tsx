import React, { useState } from "react";
import { useQuery } from "@tanstack/react-query";
import { getStudentRecord } from "../../services/UserServices";
import { Link } from "react-router-dom";

interface Report {
    id: number;
    courseName: string;
    groupId: string;
    timePosted: string;
}

const GuestPage = () => {
    const [studentId, setStudentId] = useState<string>("");

    const { isError, isLoading, refetch, data, error } = useQuery({
        queryKey: ["student-records", studentId],
        queryFn: () => getStudentRecord(studentId),
        enabled: false,
    });

    const handleSearch = async (e: React.FormEvent) => {
        e.preventDefault();
        refetch();
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
                        <button
                            type="submit"
                            className="btn btn-primary"
                            disabled={isLoading}
                        >
                            {isLoading ? "Loading..." : "Search"}
                        </button>
                    </form>
                    {isError && (
                        <div className="alert alert-danger mt-4">
                            {error.message}
                        </div>
                    )}
                </div>
            </div>
            <div className="shadow p-3 mt-5 mb-5 bg-body rounded">
                <div className="card-body p-4">
                    <table className="table table-hover  caption-top">
                        <caption className="text-dark fs-4">
                            List of reports
                        </caption>
                        <thead className="thead-light">
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Course name</th>
                                <th scope="col">Group</th>
                                <th scope="col">Time Posted</th>
                                <th scope="col">Detail</th>
                            </tr>
                        </thead>
                        <tbody>
                            {data?.data.map((report: Report, index: number) => (
                                <tr key={index + 1}>
                                    <th scope="row">{index + 1}</th>
                                    <td>{report.courseName}</td>
                                    <td>{report.groupId}</td>
                                    <td>{report.timePosted}</td>
                                    <td>
                                        <Link
                                            to={`/report/${report.id}`}
                                            className="text-info"
                                        >
                                            See Details
                                        </Link>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
};

export default GuestPage;
