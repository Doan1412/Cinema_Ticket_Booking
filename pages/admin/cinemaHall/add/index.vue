<template>
    <div class="2xl:container md:mx-auto flex flex-row bg-gray-50">
        <div class="basis-1/5">
            <Sidebar />
        </div>
        <div class="class-list basis-5/6 mt-10 bg-gray-50">
            <h2 class="app-title">Submit a Cinema Hall</h2>
            <hr style="width:90%;text-align:left;margin-left:0;margin-top: 10px;height:3px;">
            <br>
            <form class="form">
                <div class="relative z-0 w-full mb-6 group">
                    <input type="text" name="name" id="name"
                        class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=" " required v-model="cinemaHall.name" />
                    <label for="floating_first_name"
                        class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">
                        Name</label>
                </div>
                <div class="grid md:grid-cols-2 md:gap-6">
                    <!-- <div class="relative z-0 w-full mb-6 group">
                        <input type="number" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" name="capacity" id="capacity"
                            class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                            placeholder=" " required v-model="cinemaHall.capacity" />
                        <label for="floating_phone"
                            class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">
                            Capacity</label>
                    </div> -->
                    <div class="relative z-0 w-full mb-6 group">
                        <input type="number" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" name="row" id="row"
                            class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                            placeholder=" " required v-model="cinemaHall.row" />
                        <label for="floating_phone"
                            class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">
                            Row</label>
                    </div>
                    <div class="relative z-0 w-full mb-6 group">
                        <input type="number" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" name="col" id="col"
                            class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                            placeholder=" " required v-model="cinemaHall.col" />
                        <label for="floating_phone"
                            class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">
                            Collumn</label>
                    </div>
                </div>
                <div v-for="r in seatRow" :key="r" class="seat-row">
                    <div v-for="c in seatCol" :key="c" class="seat-col">
                        <!-- <label :for="`seatType${r}-${c}`">Seat Type {{ r }}-{{ c }}</label> -->
                        <select :id="`seatType${r}-${c}`" class="custom-select" v-model="type[(r - 1) * seatCol + c - 1]">
                            <option selected="selected">Regular</option>
                            <option>Premium</option>
                            <option>Accessible</option>
                            <option>EmergencyExit</option>
                            <option>Couple</option>
                        </select>
                    </div>
                </div>




                <button @click="addCineHall(); $event.preventDefault()"
                    class="mt-10 float-right relative inline-flex items-center justify-center p-0.5 mb-2 mr-2 overflow-hidden text-sm font-medium text-gray-900 rounded-lg group bg-gradient-to-br from-teal-300 to-lime-300 group-hover:from-teal-300 group-hover:to-lime-300 dark:text-white dark:hover:text-gray-900 focus:ring-4 focus:outline-none focus:ring-lime-200 dark:focus:ring-lime-800">
                    <span
                        class="relative px-5 py-2.5 transition-all ease-in duration-75 bg-white dark:bg-gray-900 rounded-md group-hover:bg-opacity-0">
                        Submit
                    </span>
                </button>
                <Notification :status="status" :object="'cinema hall'" :action="'Thêm'" :isShowNoti="showNoti"
                    v-if="showNoti == 'Ok'">
                </Notification>
            </form>
        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {
            cinemaHall: {
                name: null,
                capacity: null,
                row: null,
                col: null,
                cinemaHallSeat: [],
            },
            showNoti: '',
            status: '',
            type: []
        }
    },
    watch: {
        row(newRow) {
            this.initializeCinemaHallSeat();
        },
        col(newCol) {
            this.initializeCinemaHallSeat();
        }
    },

    mounted() {
        this.initializeCinemaHallSeat();
    },
    async fetch() {
        try {
            await this.$axios.get(`http://localhost:8080/api/movie`).then((res) => {
                this.$data.movies = res.data;
            })
            await this.$axios.get(`http://localhost:8080/api/cinemaHall/all`).then((res) => {
                this.$data.cinemaHalls = res.data;
            })
        } catch (error) {
            console.log(error);
        }
    },
    computed: {
        seatRow() {
            return parseInt(this.cinemaHall.row) || 0;
        },
        seatCol() {
            return parseInt(this.cinemaHall.col) || 0;
        },

    },
    methods: {
        initializeCinemaHallSeat() {
            const row = parseInt(this.cinemaHall.row) || 0;
            const col = parseInt(this.cinemaHall.col) || 0;

            this.type = Array.from({ length: row * col }, () => 'Regular');
        },

        async addCineHall() {

            // Assign the data to cinemaHall properties
            this.cinemaHall.capacity = this.cinemaHall.row*this.cinemaHall.col;
            for(var i = 0; i < this.cinemaHall.capacity;i++) {
                var s = {
                    row : Math.floor(i/this.cinemaHall.row)+1,
                    number : (i%this.cinemaHall.col)+1,
                    seatType : this.$data.type[i],
                }
                this.cinemaHall.cinemaHallSeat.push(s);
            }
            console.log(JSON.stringify(this.cinemaHall));
            await this.$axios
                .post("http://localhost:8080/api/cinemaHall/new", this.cinemaHall)
                .then((response) => {
                    console.log(response);
                    this.status = "thành công";
                    this.showNoti = "Ok";
                    setTimeout(() => {
                        this.showNoti = "";
                    }, 1500);
                })
                .catch((error) => {
                    console.log(error);
                });
        }
    },

}
</script>
<style scoped>
.seat-row {
    display: flex;
    margin-left: 60px;
    gap: 10px;
}

.custom-select {
    appearance: none;
    padding: 8px;
    font-size: 14px;
    border: 1px solid #ccc;
    border-radius: 4px;
    background-color: #fff;
    color: #333;
    width: 100%;
    margin:5px;
}

.custom-select:hover {
    border-color: #888;
}

.custom-select:focus {
    outline: none;
    border-color: #555;
    box-shadow: 0 0 4px rgba(85, 85, 85, 0.3);
}

.select-container {
    position: relative;
}

.select-container::after {
    content: '\25BE';
    position: absolute;
    top: 50%;
    right: 10px;
    transform: translateY(-50%);
    pointer-events: none;
}
h2 {
    font-size: 26px;
    margin: 0 0 0px 30px;
    /* text-align: center; */
}

.form {
    width: 800px;
    margin-left: 150px;
}
</style>