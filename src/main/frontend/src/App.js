import './App.css';
import NavigationBar from './components/NavigationBar';
import PopularMovies from './components/PopularMovies';
import UncontrolledExample from './components/TopMovies';


function App() {
  return (
    <div className="App">
      <NavigationBar />
      <UncontrolledExample />
      <PopularMovies />
    </div>
  );
}

export default App;
