import React from 'react';
import Grid from '@material-ui/core/Grid';
import Select from '@material-ui/core/Select';
import MenuItem from '@material-ui/core/MenuItem';
import SimpleCard from 'app/modules/SimpleCard';

class Home extends React.Component {
constructor(props) {
    super(props);
    this.state = {
        countries : [],
        allProducts:[],
        products: [],
        countryFilter:"All"
    };
  }
  getCountries(){
     let countries =["All", "France", "UK", "Germany"];
     this.setState({
         countries:countries
     });
  }

  getProducts() {
    let shop = {name:"Some",shop:"Some",country:"UK"};
    var products =[
       {name:"Foo",img_url:"https://picsum.photos/id/775/200/200",description:"Some description"},
       {name:"Foo",img_url:"https://picsum.photos/id/774/200/200",description:"Some description"},
       {name:"Foo",img_url:"https://picsum.photos/id/773/200/200",description:"Some description"},
       {name:"Foo",img_url:"https://picsum.photos/id/772/200/200",description:"Some description"},
       {name:"Foo",img_url:"https://picsum.photos/id/771/200/200",description:"Some description"},
       {name:"Foo",img_url:"https://picsum.photos/id/770/200/200",description:"Some description"},
       {name:"Foo",img_url:"https://picsum.photos/id/769/200/200",description:"Some description"},
       {name:"Foo",img_url:"https://picsum.photos/id/768/200/200",description:"Some description"},
       {name:"Foo",img_url:"https://picsum.photos/id/767/200/200",description:"Some description"},
       {name:"Foo",img_url:"https://picsum.photos/id/766/200/200",description:"Some description"},
       {name:"Foo",img_url:"https://picsum.photos/id/764/200/200",description:"Some description"}
    ];
      this.setState({
          allProducts: products,
          products: products
      })
    console.log("products FROM DB", products);
  }

  componentDidMount() {
    this.getProducts();
    this.getCountries();
  }

  filterByCountry(e) {
    let products;
    if(e.value==="All") {
         this.setState({products:this.state.allProducts});
    }
    else if(e.value!=""){
        products = this.state.allProducts.filter(p=>p.shop.country==e.value);
        this.setState({products:products});
    }
    this.setState({countryFilter:e.value});
  }

  render() {
  return (
    //input form
    <div className="App">
      <Select
          value={this.state.countryFilter}
          onChange={(e)=>{this.filterByCountry(e.target)}}
          inputProps={{
            name: 'country',
            id: "country-filter"
          }}
        >
      {this.state.countries.map(c=>

          <MenuItem key={c} value={c}>{c}</MenuItem>

      )}
      </Select>
      <Grid container justify="center" spacing={2}>
        { this.state.products.map(item =>
              <Grid key={item.id} item lg={3} sm={6}>
              <SimpleCard name={item.name}
                          description={item.description}
                          img_url={item.img_url}
                          country={"MOCK COUNTRY"}>
              </SimpleCard>
              </Grid>
          )}
      </Grid>
      
    </div>

  );
  }
}

export default Home;