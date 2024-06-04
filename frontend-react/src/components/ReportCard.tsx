import { useQuery } from "@tanstack/react-query";
import { getUserReportCount } from "../services/UserServices";
import Loading from "./Loading";

export default function ReportCard() {
    const { error, isLoading, isError, data } = useQuery({
        queryKey: ["reports-count"],
        queryFn: getUserReportCount,
    });

    if (isLoading) {
        return <Loading />;
    }

    if (isError) {
        return <span>Error: {error.message}</span>;
    }

    return (
        <>
            <div>
                <h3 className="fs-2">{data?.data}</h3>
                <p className="fs-5">Reports</p>
            </div>
        </>
    );
}
