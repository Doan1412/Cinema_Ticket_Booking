<template>
    <div class="ticketForm blockk">
        <div class="carddWrap">
            <div class="cardd carddLeft">
                <h1>Startup <span>Cinema</span></h1>
                <div class="titlee">
                    <h2>{{ moviee?.title }}</h2>
                    <span>movie</span>
                </div>
                <div class="seatt">
                    <h2>{{ show?.cinemaHall.name }}</h2>
                    <span>sceen</span>
                </div>
                <div class="timee">
                    <h2>{{ seat?.cinemaHallSeat.row }}</h2>
                    <span>row</span>
                </div>
                <div class="name s">
                    <h2>{{ seat?.cinemaHallSeat.number }}</h2>
                    <span>seat</span>
                </div>
                <div class="seatt">
                    <h2>${{ seat?.price }}</h2>
                    <span>price</span>
                </div>
                <div class="timee">
                    <h2>{{ new Date(show?.startTime).toLocaleTimeString() }}</h2>
                    <span>time</span>
                </div>
                <div class="timee">
                    <h2>{{ new Date(show?.startTime).toLocaleDateString() }}</h2>
                    <span>Date</span>
                </div>
            </div>
            <div class="cardd carddRight">
                <div class="eye"></div>
                <div class="number">
                    <h3>{{ seat?.cinemaHallSeat.row }}</h3>
                    <span>row</span>
                </div>
                <div class="number">
                    <h3>{{ seat?.cinemaHallSeat.number }}</h3>
                    <span>seat</span>
                </div>
                <div class="barcode"></div>
            </div>
        </div>
    </div>
</template>
  
<script>
export default {
    props: {
        seatId: {
            type: Number,
            default: 0
        },
        showId: {
            type: Number,
            default: 0
        },
        movieId: {
            type: String,
            default: ""
        }
    },
    data() {
        return {
            show: null,
            seat: null,
            moviee: null
        };
    },
    async fetch() {
        try {
            const responseShow = await this.$axios.get(`http://localhost:8080/api/show/id=${this.showId}`);
            this.show = responseShow.data;

            const responseMovie = await this.$axios.get(`http://localhost:8080/api/movie/${this.movieId}`);
            this.moviee = responseMovie.data;

            const responseSeat = await this.$axios.get(`http://localhost:8080/api/showseat/${this.seatId}`);
            this.seat = responseSeat.data;
            console.log(responseSeat.data);
        } catch (error) {
            console.log(error);
        }
    },
    methods: {
        formatDate(d) {
            if (d) {
                const date = new Date(d);
                return date.toDateString();
            }
            return "";
        },
        formatTime(d) {
            if (d) {
                const date = new Date(d);
                return date.toTimeString();
            }
            return "";
        }
    }
};
</script>  
<style>
.ctn3 {
    margin-top: 30px;
    width: 1000px;
}

.blockk {
    display: block;
    padding-right: 500px;
    margin-bottom: 20px;
}

.carddWrap {
    width: 50em;
    margin: 3em auto;
    color: #fff;
    font-family: sans-serif;
}

.cardd {
    background: linear-gradient(to bottom, #e84c3d 0%, #e84c3d 26%, #ecedef 26%, #ecedef 100%);
    height: 18em;
    float: left;
    position: relative;
    padding: 1em;
    margin-top: 100px;
}

.carddLeft {
    border-top-left-radius: 8px;
    border-bottom-left-radius: 8px;
    width: 30em;
}

.carddRight {
    width: 13em;
    border-left: .18em dashed #fff;
    border-top-right-radius: 8px;
    border-bottom-right-radius: 8px;

    &:before,
    &:after {
        content: "";
        position: absolute;
        display: block;
        width: .9em;
        height: .9em;
        background: #fff;
        border-radius: 50%;
        left: -.5em;
    }

    &:before {
        top: -.4em;
    }

    &:after {
        bottom: -.4em;
    }
}

h1 {
    font-size: 1.1em;
    margin-top: 5px;

    span {
        font-weight: normal;
    }
}

.titlee,
.screenn,
.seatt,
.timee {
    text-transform: uppercase;
    font-weight: normal;

    h2 {
        font-size: .9em;
        color: #525252;
        margin: 0;
    }

    span {
        font-size: .7em;
        color: #a2aeae;
    }
}

.titlee,
.name,
.seatt,
.timee {
    text-transform: uppercase;
    font-weight: normal;

    h2 {
        font-size: .9em;
        color: #525252;
        margin: 0;
    }

    span {
        font-size: .7em;
        color: #a2aeae;
    }
}

.titlee {
    margin: 4em 0 0 0;
}

.name,
.seatt {
    margin: .7em 0 0 0;
}

.s,
.timee {
    margin: .7em 0 0 1em;
}

.seatt,
.timee,
.s {
    float: left;
}

.s {
    padding-right: 20em !important;
}

.eye {
    position: relative;
    width: 2em;
    height: 1.5em;
    background: #fff;
    margin: 0 auto;
    border-radius: 1em/0.6em;
    z-index: 1;

    &:before,
    &:after {
        content: "";
        display: block;
        position: absolute;
        border-radius: 50%;
    }

    &:before {
        width: 1em;
        height: 1em;
        background: #e84c3d;
        z-index: 2;
        left: 8px;
        top: 4px;
    }

    &:after {
        width: .5em;
        height: .5em;
        background: #fff;
        z-index: 3;
        left: 12px;
        top: 8px;
    }
}

.number {
    text-align: center;
    text-transform: uppercase;
    display: inline-block;
    margin-left: 2em;

    h3 {
        color: #e84c3d;
        margin: 1.5em 0 0 0;
        font-size: 2.5em;

    }

    span {
        display: block;
        color: #a2aeae;
    }
}

.barcode {
    height: 2em;
    width: 0;
    margin: 1.2em 0 0 3em;
    box-shadow: 1px 0 0 1px #343434,
        5px 0 0 1px #343434,
        10px 0 0 1px #343434,
        11px 0 0 1px #343434,
        15px 0 0 1px #343434,
        18px 0 0 1px #343434,
        22px 0 0 1px #343434,
        23px 0 0 1px #343434,
        26px 0 0 1px #343434,
        30px 0 0 1px #343434,
        35px 0 0 1px #343434,
        37px 0 0 1px #343434,
        41px 0 0 1px #343434,
        44px 0 0 1px #343434,
        47px 0 0 1px #343434,
        51px 0 0 1px #343434,
        56px 0 0 1px #343434,
        59px 0 0 1px #343434,
        64px 0 0 1px #343434,
        68px 0 0 1px #343434,
        72px 0 0 1px #343434,
        74px 0 0 1px #343434,
        77px 0 0 1px #343434,
        81px 0 0 1px #343434;
}</style>