import Table from "react-bootstrap/Table";
import React, { useState, useEffect } from "react";
import axios from "axios";
import Tab from "react-bootstrap/Tab";
import Tabs from "react-bootstrap/Tabs";
import NavigationBar from "./NavigationBar";
import AddMovie from "./AddMovie";
import Register from "./Register";

function Admin() {
  const [accounts, setAccounts] = useState([]);
  const [movies, setMovies] = useState([]);
  const [key, setKey] = useState("accounts");
  const token = localStorage.getItem("jwtToken");

  useEffect(() => {
    const fetchData = async () => {
      try {
        const accountResponse = await axios.get(
          "http://localhost:8080/api/v1/accounts",
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );

        setAccounts(accountResponse.data);

        const movieResponse = await axios.get(
          "http://localhost:8080/api/v1/movies",
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );

        setMovies(movieResponse.data);
        console.log(movieResponse.data);
      } catch (error) {
        console.error("Error fetching movies table:", error);
      }
    };

    fetchData();
  }, []);

  const handleDelete = async (record, type) => {
    try {
      const endpoint = type === "account" ? "account" : "movie";
      const params =
        type === "account" ? { id: record.id } : { id: record.movieId };
      await axios.delete(`http://localhost:8080/api/v1/${endpoint}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
        params,
      });
      alert(`Record deleted from ${type}s!`);

      if (type === "account") {
        setAccounts((prevAccounts) =>
          prevAccounts.filter((item) => item.id !== record.id)
        );
      } else if (type === "movie") {
        setMovies((prevMovies) =>
          prevMovies.filter((item) => item.movieId !== record.movieId)
        );
      }
    } catch (error) {
      console.error("Error while deleting:", error);
      alert("Failed to delete the record!");
    }
  };

  return (
    <div>
      <NavigationBar />

      <Tabs
        id="tabs"
        className="mb-3"
        onSelect={(k) => setKey(k)}
        activeKey={key}
      >
        <Tab eventKey="accounts" title="Accounts">
          <Table striped bordered hover>
            <thead>
              <tr>
                <th>id</th>
                <th>email</th>
                <th>username</th>
                <th>password</th>
                <th>role</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              {accounts.map((account, index) => (
                <tr key={index}>
                  <td>{account.id}</td>
                  <td>{account.email}</td>
                  <td>{account.username}</td>
                  <td>{account.password}</td>
                  <td>{account.role}</td>
                  <td onClick={() => handleDelete(account, "account")}>
                    <i className="bi bi-x-circle-fill text-danger" />
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
          <button
            className="btn btn-primary"
            onClick={() => setKey("addAccount")}
          >
            <i className="bi bi-plus-circle-fill"></i> Add
          </button>
        </Tab>
        <Tab eventKey="movies" title="Movies">
          <Table striped bordered hover>
            <thead>
              <tr>
                <th>id</th>
                <th>tmdb_id</th>
                <th>title</th>
                <th>release_date</th>
                <th>vote_average</th>
                <th>runtime</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              {movies.map((movie, index) => (
                <tr key={index}>
                  <td>{movie.movieId}</td>
                  <td>{movie.tmdbMovieId}</td>
                  <td>{movie.title}</td>
                  <td>{movie.releaseDate}</td>
                  <td>{movie.voteAverage}</td>
                  <td>{movie.runtime}</td>
                  <td onClick={() => handleDelete(movie, "movie")}>
                    <i className="bi bi-x-circle-fill text-danger" />
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
          <button
            className="btn btn-primary"
            onClick={() => setKey("addMovie")}
          >
            <i className="bi bi-plus-circle-fill"></i> Add
          </button>
        </Tab>
        <Tab eventKey="addAccount" title="Add Account">
          <Register />
        </Tab>
        <Tab eventKey="addMovie" title="Add Movies">
          <AddMovie />
        </Tab>
      </Tabs>
    </div>
  );
}

export default Admin;
