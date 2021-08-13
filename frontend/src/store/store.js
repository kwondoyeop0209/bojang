import Vue from 'vue';
import Vuex from 'vuex';
import http from "@/util/http-common";
import { TOKEN } from './mutation-types';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    markets: [], // 전체 마켓들
    market: [], // 선택된 마켓(id,name)
    token: '',
    isLogin: false,
    frequentStore: [], // 전체 마켓들
    stores: [], // (마켓, 카테고리 내)전체 가게들
    store: [], // 마켓에서 선택된 가게

  },
  getters: {
    markets(state){
      return state.markets;
    },
    market(state){
      return state.market;
    },
    frequentStore(state){
      return state.frequentStore;
    },
    getToken: (state) => {
      return state.token;
    },
    getIsLogin: (state) => {
        return state.isLogin;
    },
    stores(state){
      return state.stores;
    },
    store(state){
      return state.store;
    }
  },
  mutations: { // state 값 변경하는 함수.
    setMarkets(state, payload) {
      state.markets = payload;
    },
    setMarket(state, payload){ // 선택된 market명으로 변경
      state.market = payload;
    },
    setFrequentStore(state, payload){ // 선택된 market명으로 변경
      state.frequentStore = payload;
    },
    setToken(state, payload){
      state.token = payload;
    },
    setIsLogin(state){
      if (localStorage.getItem("token")) {
        state.isLogin = true;
    } else {
        state.isLogin = false;
    }
    },
    setStores(state, payload) {
      state.stores = payload;
    },
    setStore(state, payload){
      state.store = payload;
    }
  },
  actions: { // 비동기 처리
    getMarkets(context) {
      http
        .get("/api/market")
        .then(( data ) => {
          context.commit("setMarkets", data.data.marketList);
        })
        .catch(() => {
          alert("getMarkets 오류 발생");
        });
    },
    getStores(context,payload) { // ​/api​/store​/category 해당 마켓의 카테고리별 가게 리스트
      http
        .get(payload)
        .then(( data ) => {
          context.commit("setStores", data.data.storeList);
        })
        .catch(() => {
          alert("getStores 오류 발생");
        });
    },
    getStore(context,payload) { // ​​/api​/store​/{storeId} 해당 ID의 가게 정보
      http
        .get(payload)
        .then(( data ) => {
          context.commit("setStore", data.data.store);
        })
        .catch(() => {
          alert("getStore 오류 발생");
        });
    },
    getFrequentStore(context) {
      http
        .get("/api/favorite", {
          // header token 팁
          headers: {
            // Authorization: `Bearer `+ this.state.token
            Authorization: `Bearer `+ this.getters.getToken
          }})
        .then(( data ) => {
          context.commit("setFrequentStore", data.data.favoriteStoreList);
        })
        .catch(() => {
          alert("수행 중 오류가 발생했습니다.");
        });
    },
    getToken({ commit }, token) {
      commit("setToken", token);
      // token 출력 됨
      // console.log(token)
    },
    getIsLogin({ commit }, isLogin) {
      commit("setIsLogin", isLogin);
      // console.log(isLogin)
    }
  },
});
