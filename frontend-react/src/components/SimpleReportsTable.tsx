import { useQuery } from "@tanstack/react-query";
import { getUserSimpleReports } from "../services/UserServices";
import Loading from "./Loading";
import { toast } from "react-toastify";
import { Link } from "react-router-dom";

type SimpleReport = {
    id: number;
    courseName: string;
    groupId: string;
    timePosted: string;
};

export default function SimpleReportsTable() {
    const { data, isLoading, isError, error } = useQuery({
        queryKey: ["simple-reports"],
        queryFn: getUserSimpleReports,
    });

    if (isLoading) {
        return <Loading />;
    }

    if (isError) {
        toast.error(error.message);
    }

    return (
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
                        {data?.data.map(
                            (report: SimpleReport, index: number) => (
                                <tr key={index + 1}>
                                    <th scope="row">{index + 1}</th>
                                    <td>{report.courseName}</td>
                                    <td>{report.groupId}</td>
                                    <td>{report.timePosted}</td>
                                    <td>
                                        <Link
                                            to={`/home/report/${report.id}`}
                                            className="text-info"
                                        >
                                            See Details
                                        </Link>
                                    </td>
                                </tr>
                            )
                        )}
                    </tbody>
                </table>
            </div>
        </div>
    );
}
