import { useQuery } from "@tanstack/react-query";
import { getStudentRecords } from "../../services/UserServices";
import { Link, useParams } from "react-router-dom";

type studentScores = {
    studentName: string;
    classId: string;
    inclass: number;
    midterm: number;
    finalField: number;
    description: string;
};

type Report = {
    id: string;
    courseId: string;
    courseName: string;
    lecturerId: string;
    lecturerName: string;
    timePosted: string;
    studentScores: studentScores[];
};

const StudentRecords: React.FC = () => {
    const { studentID } = useParams();
    const { isError, isSuccess, isLoading, data, error } = useQuery({
        queryKey: ["student-records", studentID],
        queryFn: () => getStudentRecords(studentID!),
    });

    const reports: Report[] = data?.data;

    if (isError) {
        console.log(error.message);
    }

    return (
        <div>
            <div className="container mt-5">
                <h1 className="text-center mb-4">Student Record</h1>
                {isLoading && <div className="text-center">Loading...</div>}
                {isError && (
                    <div className="alert alert-danger text-center">
                        {error.message}
                    </div>
                )}
                {isSuccess &&
                    reports?.map((report, index) => (
                        <div className="card mb-4" key={index}>
                            <div className="card-header bg-primary text-white">
                                <h2 className="h5">{report.courseName}</h2>
                            </div>
                            <div className="card-body">
                                <ul className="list-group list-group-flush">
                                    <li className="list-group-item">
                                        <span className="fw-bold">
                                            Course ID :{" "}
                                        </span>
                                        {report.courseId}
                                    </li>
                                    <li className="list-group-item">
                                        <span className="fw-bold">
                                            Lecturer :{" "}
                                        </span>
                                        {report.lecturerName}
                                    </li>
                                    <li className="list-group-item">
                                        <span className="fw-bold">
                                            Lecturer ID :{" "}
                                        </span>
                                        {report.lecturerId}
                                    </li>
                                    <li className="list-group-item">
                                        <span className="fw-bold">
                                            Time posted :{" "}
                                        </span>
                                        {report.timePosted}
                                    </li>
                                </ul>
                                <table className="table table-bordered table-hover mt-3">
                                    <thead className="thead-light">
                                        <tr>
                                            <th scope="col">Name</th>
                                            <th scope="col">Class</th>
                                            <th scope="col">Inclass</th>
                                            <th scope="col">Midterm</th>
                                            <th scope="col">Final</th>
                                            <th scope="col">Description</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>
                                                {
                                                    report.studentScores[0]
                                                        .studentName
                                                }
                                            </td>
                                            <td>
                                                {
                                                    report.studentScores[0]
                                                        .classId
                                                }
                                            </td>
                                            <td>
                                                {
                                                    report.studentScores[0]
                                                        .inclass
                                                }
                                            </td>
                                            <td>
                                                {
                                                    report.studentScores[0]
                                                        .midterm
                                                }
                                            </td>
                                            <td>
                                                {
                                                    report.studentScores[0]
                                                        .finalField
                                                }
                                            </td>
                                            <td>
                                                {
                                                    report.studentScores[0]
                                                        .description
                                                }
                                            </td>
                                            <td>
                                                <Link
                                                    to={`/home/report/${report.id}`}
                                                    className="text-info"
                                                >
                                                    See Details
                                                </Link>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    ))}
            </div>
        </div>
    );
};

export default StudentRecords;
