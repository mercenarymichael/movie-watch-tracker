import Carousel from 'react-bootstrap/Carousel';
import './CarouselCss.css';

function UncontrolledExample() {
  return (
    <div className='carousel'>
        <Carousel>
            <Carousel.Item>
                <img
                src="https://image.tmdb.org/t/p/original/8ZTVqvKDQ8emSGUEMjsS4yHAwrp.jpg"
                alt="First slide"
                />
                <div className='image-overlay'/>
                <Carousel.Caption>
                <h3>First slide label</h3>
                <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
                </Carousel.Caption>
                
            </Carousel.Item>
            <Carousel.Item>
                <img
                src="https://image.tmdb.org/t/p/original/xJHokMbljvjADYdit5fK5VQsXEG.jpg"
                alt="Second slide"
                />
                <Carousel.Caption style={{ color: 'red' }}>
                <h3>Second slide label</h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                </Carousel.Caption>
                <div className='image-overlay'/>
            </Carousel.Item>
            <Carousel.Item>
                <img
                src="https://image.tmdb.org/t/p/original/9faGSFi5jam6pDWGNd0p8JcJgXQ.jpg"
                alt="Third slide"
                />
                <Carousel.Caption style={{ color: 'red' }}>
                <h3>Third slide label</h3>
                <p>Praesent commodo cursus magna, vel scelerisque nisl consectetur.</p>
                </Carousel.Caption>
                <div className='image-overlay'/>
            </Carousel.Item>
        </Carousel>
    </div>
  );
}

export default UncontrolledExample;
