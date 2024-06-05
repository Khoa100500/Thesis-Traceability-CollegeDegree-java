import { ReportRequest } from "../components/Pages/Home";
import { FormData } from "formdata-node";
import instance from "./axios-instance";

const getUserReportCount = async () => {
    return await instance.get("/user/reports/count");
};

const getUserSimpleReports = async () => {
    return await instance.get("/user/simple-reports");
};

const getReport = async (reportID: string) => {
    return await instance.get(`/report/${reportID}`);
};

const getStudentReport = async (reportID: string) => {
    return await instance.get(`/guest/report/${reportID}`);
};

const getStudentRecord = async (studentId: string) => {
    return await instance.get(`/guest/simple-reports/${studentId}`);
};

const handleUpload = async (data: ReportRequest) => {
    const formData = new FormData();
    formData.append("file", data.file);
    // make a POST request to the File Upload API with the FormData object and Rapid API headers
    return await instance.post(`/user/report`, formData, {
        headers: {
            "Content-Type": "multipart/form-data",
        },
    });
};

export {
    handleUpload,
    getUserReportCount,
    getUserSimpleReports,
    getReport,
    getStudentRecord,
    getStudentReport,
};
