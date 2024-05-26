import Nav from "./Nav";

interface StudentScore {
    name: string;
    score: number;
    final: number;
    midterm: number;
}

interface ClassInfo {
    className: string;
    teacher: string;
    period: string;
}

export default function Report() {
    // Mock data
    const classInfo: ClassInfo = {
        className: "Mathematics 101",
        teacher: "Mr. John Doe",
        period: "Spring 2024",
    };

    const studentScores: StudentScore[] = [
        { name: "Alice Johnson", score: 95, final: 90, midterm: 56 },
        { name: "Alice Johnson", score: 95, final: 90, midterm: 56 },
        { name: "Alice Johnson", score: 95, final: 90, midterm: 56 },
        { name: "Alice Johnson", score: 95, final: 90, midterm: 56 },
        { name: "Alice Johnson", score: 95, final: 90, midterm: 56 },
        { name: "Alice Johnson", score: 95, final: 90, midterm: 56 },
        { name: "Alice Johnson", score: 95, final: 90, midterm: 56 },
    ];

    return (
        <>
            <Nav />
            <div className="container mt-5">
                {/* Class Information Section */}
                <div className="mb-4">
                    <h2 className="text-white">Class Information</h2>
                    <div className="card p-3">
                        <p>
                            <strong>Class Name:</strong> {classInfo.className}
                        </p>
                        <p>
                            <strong>Teacher:</strong> {classInfo.teacher}
                        </p>
                        <p>
                            <strong>Period:</strong> {classInfo.period}
                        </p>
                    </div>
                </div>

                {/* Scores Table Section */}
                <div>
                    <h2 className="text-white">Student Scores</h2>
                    <div className="card p-3">
                        <table className="table table-striped">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Score</th>
                                </tr>
                            </thead>
                            <tbody>
                                {studentScores.map((student, index) => (
                                    <tr key={index}>
                                        <td>{student.name}</td>
                                        <td>{student.score}</td>
                                        <td>{student.final}</td>
                                        <td>{student.midterm}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </>
    );
}
