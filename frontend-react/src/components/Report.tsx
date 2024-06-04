import { useParams } from "react-router-dom";
import Nav from "./Nav";
import Loading from "./Loading";
import { toast } from "react-toastify";
import { getReport } from "../services/UserServices";
import { useQuery } from "@tanstack/react-query";
import instance from "../services/axios-instance";

type studentScores = {
    studentId: string;
    studentName: string;
    birthday: string;
    classId: string;
    inclass: number;
    midterm: number;
    finalField: number;
    description: string;
};

type user = {
    userFullname: string;
};

type sign = {
    isVerifiedAgainstRoot: boolean;
    issuer: string;
    subject: string;
    validFrom: string;
    validTo: string;
    validityNow: string;
    validityOnSign: string;
    contactInfo: string;
    digestAlgorithm: string;
    encryptionAlgorithm: string;
    fieldOnPage: number;
    filterSubtype: string;
    integrity: boolean;
    isAnnotationsAllowed: boolean;
    isFillInAllowed: boolean;
    isSignatureInvisible: boolean;
    isTimeStampVerified: boolean;
    location: string;
    reason: string;
    revision: string;
    signatureCoversWholeDocument: true;
    signatureType: string;
    signedOn: string;
    signerAlternativeName: string;
    signerName: string;
    timeStamp: string;
    timeStampService: string;
};

type Report = {
    user: user;
    courseId: string;
    courseName: string;
    groupId: string;
    hpUNIT: string;
    lecturerId: string;
    lecturerName: string;
    timePosted: string;
    studentScores: studentScores[];
    sign: sign;
};

export default function Report() {
    const { reportID } = useParams();
    const { data, isLoading, isError, error } = useQuery({
        queryKey: ["simple-reports", reportID],
        queryFn: () => getReport(reportID!),
    });

    const signData = data?.data.sign;

    if (isLoading) {
        return <Loading />;
    }

    if (isError) {
        toast.error(error.message);
    }

    return (
        <>
            <Nav />
            <div className="container-fluid">
                <div className="row mb-4">
                    <div className="col-md-5">
                        <h2 className="text-black">Signer detail</h2>
                        <div className="card p-3">
                            <ul className="list-group">
                                <li className="list-group-item">
                                    Course ID : {data?.data.courseId}
                                </li>
                                <li className="list-group-item">
                                    Course name : {data?.data.courseName}
                                </li>
                                <li className="list-group-item">
                                    Group ID : {data?.data.groupId}
                                </li>
                                <li className="list-group-item">
                                    hpUNIT : {data?.data.hpUNIT}
                                </li>
                                <li className="list-group-item">
                                    Lecturer ID : {data?.data.lecturerId}
                                </li>
                                <li className="list-group-item">
                                    Lecturer name : {data?.data.lecturerName}
                                </li>
                                <li className="list-group-item">
                                    Time posted : {data?.data.timePosted}
                                </li>
                                <li className="list-group-item">
                                    User fullname :{" "}
                                    {data?.data.user.userFullname}
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div className="col-md-5">
                        <h2 className="text-black">Signer detail</h2>
                        <div className="card p-3">
                            <ul className="list-group">
                                {Object.keys(signData).map((key, i) => (
                                    <li key={i} className="list-group-item">
                                        {key} : {signData[key] + ""}
                                    </li>
                                ))}
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div className="shadow p-3 mb-5 bg-body rounded">
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
                            {data?.data.studentScores.map(
                                (student: studentScores, index: number) => (
                                    <tr key={index + 1}>
                                        <th scope="row">{index + 1}</th>
                                        <td>{student.studentId}</td>
                                        <td>{student.studentName}</td>
                                        <td>{student.birthday}</td>
                                        <td>{student.classId}</td>
                                        <td>{student.inclass}</td>
                                        <td>{student.midterm}</td>
                                        <td>{student.finalField}</td>
                                        <td>{student.description}</td>
                                    </tr>
                                )
                            )}
                        </tbody>
                    </table>
                </div>
            </div>
        </>
    );
}
