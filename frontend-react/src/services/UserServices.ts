import API from "./axios-instance";

const fetchAllUser = () => {
    return API.get("/pdf");
};

export { fetchAllUser };
