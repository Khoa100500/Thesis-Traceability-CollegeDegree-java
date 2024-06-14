import Nav from "../Nav";
import { useState } from "react";
import { useMutation } from "@tanstack/react-query";
import { handleUpload } from "../../services/UserServices";
import ReportCard from "../ReportCard";
import SimpleReportsTable from "../SimpleReportsTable";
import { useNavigate } from "react-router-dom";
import React from "react";
import { toast } from "react-toastify";

export type ReportRequest = {
    file: File | null;
};

export default function Home() {
    const [selectedFile, setSelectedFile] = useState<File | null>(null);
    const navigate = useNavigate();
    const mutation = useMutation({
        mutationFn: handleUpload,
        onError() {
            toast.error("Invalid input");
        },
        onSuccess(data) {
            toast.success("Report upload success");
            navigate(`/home/report/${data.data}`);
        },
    });

    const handleFileSelect = (e: React.ChangeEvent<HTMLInputElement>) => {
        if (!e.target.files) return;
        setSelectedFile(e.target.files[0]);
    };

    const onClick = () => {
        mutation.mutate({ file: selectedFile });
    };

    return (
        <div className="px-3 ">
            <Nav />
            <div className="container-fluid shadow p-3 mb-5 bg-body rounded">
                <div className="row g-3 my-2">
                    <div className="col-md-4 p-4">
                        <div className="p-3 bg-white d-flex justify-content-around align-items-center rounded">
                            <div className="input-group">
                                <input
                                    type="file"
                                    accept=".pdf"
                                    className="form-control"
                                    id="inputGroupFile04"
                                    aria-describedby="inputGroupFileAddon04"
                                    aria-label="Upload"
                                    onChange={handleFileSelect}
                                ></input>
                                <button
                                    className="btn btn-outline-secondary"
                                    type="button"
                                    id="inputGroupFileAddon04"
                                    onClick={onClick}
                                >
                                    Upload report
                                </button>
                            </div>
                        </div>
                    </div>
                    <div className="col-md-2 p-1 ">
                        <div className="p-3 bg-white d-flex justify-content-around align-items-center bg-body">
                            <ReportCard />
                        </div>
                    </div>
                </div>
            </div>
            <SimpleReportsTable />
        </div>
    );
}
