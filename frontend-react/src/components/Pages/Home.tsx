import axios from "axios";
import Nav from "../Nav";
import { useState } from "react";
import { FormData } from "formdata-node";
import { useNavigate } from "react-router-dom";

export default function Home() {
    const [selectedFile, setSelectedFile] = useState<File | null>(null);
    const navigate = useNavigate();

    const handleFileSelect = (e: React.ChangeEvent<HTMLInputElement>) => {
        if (!e.target.files) return;
        setSelectedFile(e.target.files[0]);
    };

    const handleUpload = () => {
        const formData = new FormData();
        formData.append("file", selectedFile);
        // make a POST request to the File Upload API with the FormData object and Rapid API headers
        axios
            .post("/api/v1/auth/pdf", formData, {
                headers: {
                    "Content-Type": "multipart/form-data",
                },
            })
            .then((response) => {
                // handle the response
                navigate("/home/dashboard", { state: { response } });
            })
            .catch((error) => {
                // handle errors
                console.log(error);
            });
    };

    return (
        <div className="px-3">
            <Nav />
            <div className="container-fluid">
                <div className="row g-3 my-2">
                    <div className="col-md-6 p-1">
                        <div className="p-3 bg-white shaow-sm d-flex justify-content-around align-items-center rounded">
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
                                    onClick={handleUpload}
                                >
                                    Upload
                                </button>
                            </div>
                        </div>
                    </div>

                    <div className="col-md-3 p-1">
                        <div className="p-3 bg-white shaow-sm d-flex justify-content-around align-items-center rounded">
                            <div>
                                <h3 className="fs-2">2250</h3>
                                <p className="fs-5">Deliveries</p>
                            </div>
                            <i className="bi bi-truck p-3 fs-1"></i>
                        </div>
                    </div>
                    <div className="col-md-3 p-1">
                        <div className="p-3 bg-white shaow-sm d-flex justify-content-around align-items-center rounded">
                            <div>
                                <h3 className="fs-2">20%</h3>
                                <p className="fs-5">Increases</p>
                            </div>
                            <i className="bi bi-graph-up-arrow p-3 fs-1"></i>
                        </div>
                    </div>
                </div>
            </div>
            <table className="table table-striped caption-top mt-2">
                <caption className="text-white fs-4 ">List of reports</caption>
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">First</th>
                        <th scope="col">Last</th>
                        <th scope="col">Handle</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th scope="row">1</th>
                        <td>Mark</td>
                        <td>Otto</td>
                        <td>@mdo</td>
                    </tr>
                    <tr>
                        <th scope="row">2</th>
                        <td>Jacob</td>
                        <td>Thornton</td>
                        <td>@fat</td>
                    </tr>
                    <tr>
                        <th scope="row">3</th>
                        <td>Larry</td>
                        <td>The Bird</td>
                        <td>@twitter</td>
                    </tr>
                </tbody>
            </table>
        </div>
    );
}
