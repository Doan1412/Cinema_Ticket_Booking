<template>
    <div class="ctn2">
      <h3 class="mb-10 mt-5 title text-lg font-medium leading-none text-gray-900 dark:text-white">Seat selection</h3>
      <div class="container">
        <div class="screen">Screen</div>
        <div v-for="r in row" :key="r" class="row">
          <div v-for="c in col" :key="c">
            <button :class="getSeatClass((r - 1) * col + c - 1)" @click="toggleSeat((r - 1) * col + c - 1)"></button>
          </div>
        </div>
        <div class="mt-10">
  
          <button type="button"
            class="text-white bg-red-600 dark:bg-blue-500  inline-flex cursor-not-allowed text-xs font-medium rounded-lg px-3 py-2 text-center"
            disabled>Current selection</button>
            <button type="button"
            class="text-white bg-orange-400 dark:bg-blue-500  inline-flex cursor-not-allowed text-xs font-medium rounded-lg px-3 py-2 text-center"
            disabled>Seat occupied</button>
            <button type="button"
            class="text-white bg-gray-700 dark:bg-blue-500  inline-flex cursor-not-allowed text-xs font-medium rounded-lg px-3 py-2 text-center"
            disabled>Seat available</button>
        </div>
        <button type="submit"
          class="text-white bg-gradient-to-r from-cyan-500 to-blue-500 hover:bg-gradient-to-bl focus:ring-4 focus:outline-none focus:ring-cyan-300 dark:focus:ring-cyan-800 font-medium rounded-lg text-sm px-5 py-2.5 text-center mb-2 left1"
          @click="next(); $event.preventDefault()">
          Next Step: Selete Payment Infor
        </button>
      </div>
  
    </div>
  </template>
  <script>
  export default {
    props: {
      showId: {
        type: Number,
        default: 0,
      },
    },
    data() {
      return {
        show: null,
        row: null,
        col: null,
        reserved: [],
        seatChoice: [],
        listId: [],
      }
    },
    async fetch() {
      try {
        let url = `http://localhost:8080/api/show/id=${this.showId}`;
        console.log(url);
        await this.$axios.get(url).then((response) => {
          this.$data.show = response.data;
          this.$data.row = this.show.cinemaHall.row;
          this.$data.col = this.show.cinemaHall.col;
          this.setListReserved();
        });
      }
      catch (error) {
        console.log(error);
      }
    },
    methods: {
      setListReserved() {
        this.reserved = Array(this.show.cinemaHall.capacity)
          .fill(0);
        this.listId = Array(this.show.cinemaHall.capacity).fill(0);
        let i=0;
        for (const seat of this.show.showSeats) {
          this.listId [i] = seat.id;
          seat.reserved ? this.reserved[this.listId[i]] = 1 : this.reserved[this.listId[i]] = 0;
          i++;
        }
        console.log(this.listId);
      },
      toggleSeat(seatId) {
        if (this.reserved[this.listId[seatId]] == 1) {
          // Seat is reserved, cannot be selected
          return;
        }
        const index = this.seatChoice.indexOf(this.listId[seatId]);
        if (index === -1) {
          // Seat is not in the seatChoice array, add it
          this.seatChoice.push(this.listId[seatId]);
          this.reserved[this.listId[seatId]] = 2;
        } else {
          // Seat is already in the seatChoice array, remove it
          this.reserved[this.listId[seatId]] = 0;
          this.seatChoice.splice(index, 1);
        }
        console.log(this.seatChoice);
      },
      getSeatClass(seatId) {
        if (this.seatChoice.includes(this.listId[seatId])) {
          return 'choice';
        } else if (this.reserved[this.listId[seatId]]) {
          return 'seat occupied';
        } else {
          return 'seat';
        }
      },
      next() {
        if (this.seatChoice == null || this.seatChoice.length == 0) {
          alert("Please select at least one seat");
          return;
        }
        console.log(this.seatChoice);
        this.$emit("listSeat", this.seatChoice);
      }
    }
  }
  </script>
  <style>
  @import url('https://fonts.googleapis.com/css?family=Lato&display=swap');
  
  .ctn2 {
    margin-top: 30px;
  }
  
  * {
    box-sizing: border-box;
  }
  
  .movie-container {
    margin: 20px 0;
  }
  
  .title {
    /* text-align: center; */
    margin-left: 30%;
  }
  
  .container {
    perspective: 1000px;
    margin-bottom: 30px;
    margin-left: 10%;
  }
  
  .left1 {
    margin-left: 450px !important;
    margin-top: 50px;
  }
  
  .seat {
    background-color: #444451;
    height: 32px;
    width: 40px;
    margin: 10px;
    border-top-left-radius: 30px;
    border-top-right-radius: 30px;
  }
  
  .seat.selected {
    background-color: #6feaf6;
  }
  
  .seat.occupied {
    background-color: orange;
  }
  
  .choice {
    background-color: rgb(255, 0, 0) !important;
    height: 32px;
    width: 40px;
    margin: 10px;
    border-top-left-radius: 30px;
    border-top-right-radius: 30px;
  }
  
  .seat:nth-of-type(2) {
    margin-right: 18px;
  }
  
  .seat:nth-last-of-type(2) {
    margin-left: 18px;
  }
  
  .seat:not(.occupied):hover {
    cursor: pointer;
    transform: scale(1.2);
  }
  
  .showcase .seat:not(.occupied):hover {
    cursor: default;
    transform: scale(1);
  }
  
  .row {
    display: flex;
    margin-left: 60px;
  }
  
  .screen {
    background-color: #000000;
    height: 70px;
    width: 600px;
    margin: 25px 0 40px 0;
    transform: rotateX(-45deg);
    box-shadow: 0 3px 10px rgba(255, 255, 255, 0.75);
    color: #ffffff;
    text-align: center;
    padding: 5px 0;
  }
  
  p.text {
    margin: 5px 0;
  }
  
  p.text span {
    color: #6feaf6;
  }
  </style>