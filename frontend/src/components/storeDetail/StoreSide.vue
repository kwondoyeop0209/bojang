<template>
  <v-container>
    <v-btn
      elevation="5"
      x-large
      to="mainpage"
    >
      <v-icon
        dark
        left
      >
        mdi-arrow-left
      </v-icon>
      시장 둘러보기
    </v-btn>

    <!-- 스토어 이름/이미지/설명 -->
    <v-container
      ma-5
    >
      <v-row>
        <v-img
          :src="'https://i5a508.p.ssafy.io:8081/api/store/downloadFile/' + $store.getters.store.image"
        />
      </v-row>
      <v-row>
        <h3>
          {{ $store.getters.store.name }}
        </h3>
      </v-row>
      <v-row>
        {{ $store.getters.store.comment }}
      </v-row>
    </v-container>

    <!-- 단골가게 등록 버튼 -->
    <div class="text-center">
      <v-dialog
        v-model="dialog"
        width="500"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            color="red lighten-2"
            dark
            v-bind="attrs"
            v-on="on"
            @click="addFavoriteStore"
          >
            <v-icon
              dark
              left
            >
              mdi-heart
            </v-icon>
            단골가게 등록
          </v-btn>
        </template>

        <v-card>
          <v-card-title class="grey lighten-2">
            단골가게 등록
          </v-card-title>

          <v-card-text>
            해당 가게를 단골가게로 추가했습니다.
          </v-card-text>

          <v-divider />

          <v-card-actions>
            <v-spacer />
            <v-btn
              color="primary"
              text
              @click="dialog = false"
            >
              확인
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>

    <!-- 호출버튼 -->
    <div class="text-center">
      <v-bottom-sheet
        v-model="sheet"
        inset
      >
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            color="orange"
            dark
            v-bind="attrs"
            x-large
            text
            v-on="on"
          >
            <v-icon
              dark
              left
            >
              mdi-bell
            </v-icon>
            사장님 호출
          </v-btn>
        </template>
        <v-sheet
          class="text-center"
          height="200px"
        >
          <v-btn
            class="mt-6"
            text
            color="error"
            @click="sheet = !sheet"
          >
            확인
          </v-btn>
          <div class="my-3">
            사장님께 방문을 알렸어요. <br>
            잠시만 기다려주세요.
          </div>
        </v-sheet>
      </v-bottom-sheet>
    </div>
  </v-container>
</template>

<style>
/* Helper classes */
.basil {
  background-color: #FFFBE6 !important;
}
.vll {
  background-color: #FF6F61 !important;
}
.color1{
  background-color: #cff0da   !important;
}
.color2{
  background-color: #88dba3   !important;
}
.color3{
  background-color: #dadbdb    !important;
}
.color4{
  background-color: #3ac569 !important;
}
.basil--text {
  color: #3ac569 !important;
}
</style>

<script>
import {mapGetters} from "vuex";
import axios from "axios";

export default {
  name: 'StoreSide',
  data: () => ({
    sheet: false,
    dialog: false,
  }),

  methods: {
    next() {
      alert('단골가게로 등록되었습니다.')
    },
    addFavoriteStore(){
      axios({
        method:'post',
        url:'https://i5a508.p.ssafy.io:8081/api/favorite',
        headers:{
          Authorization: `Bearer `+ this.$store.getters.getToken
        },
        data:{
          storeId: this.$store.getters.store.id,
        }
      })
      .then(() =>{
        console.log('ttt')
      })
    }

  }
}
</script>
