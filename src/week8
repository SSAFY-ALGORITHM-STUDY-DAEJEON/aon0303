#include <bits/stdc++.h>
using namespace std;

/*
목표 : 모든 과목을 완료하는데 몇 학기가 필요한가?
알고리즘 : 선행관계 방향 그래프 = 위상정렬

cnt가 들어온다, indegree 배열로 가지고 있는 갯수 세기 
graph idx = 과목이름 
1. 안에 선수과목들을 idx 후수과목에 넣는다 
2. graph를 돌면서 indegree =0인 경우를 찾고 거기에 해당하는 indegree -=1 한다 
3. 레벨이 같은 경우는 한번에 처리해야한다 queue
4. 레벨이 갱신될때 Lcnt ++


주의 
그래프에는 indegree와 outdegree가 있다 
indegree는 후에 선을 넣는 것  -> 얘를 cnt로 조정하고 
outdegree는 선에 후를 넣는 것  -> 얘를 graph로 가지고 있는게 위상정렬 
"나를 선수과목으로 가지고 있는 애들은?"

모든 과목을 완료하는 것이 불가능할 수 있음 -1
*/
int T, N;
int indegree[101];
int completeCnt, Lcnt;
int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> T;
    for(int t = 1; t <= T; t++){
        cin >> N;
        vector <vector<int>> graph(N+1);
        Lcnt =0;
        //1. graph , indegree setup
        for(int n =1; n <= N; n++){
            int cnt;
            cin >> cnt;

            indegree[n] = cnt;

            for(int c =0; c < cnt; c++){ //현재 인덱스가 가지고 있는 선수과목 갯수
                int pre;
                cin >> pre; //들어오는 선수과목 번호
                graph[pre].push_back(n);

            }
        }

        //여기서 바깥 for문을 돌면서 학기 마다 수강과목을 넣어줄 필요없이 size로 while 문 안에서 
        //queue에 넣어줄 수 있다 
        //for(int j = 0; j< N ; j++){
            queue<int> q;
            completeCnt =0;
            //2. indegree에서 0인 경우를 찾자
            //가장 깊은 레벨을 가져오자
            for(int i =1; i<=N; i++){
                if(indegree[i] ==0){
                    q.push(i);
                    completeCnt ++;
                }
            }
    
            //3. indegree 갱신을 위해 위에서 넣은 idx를 가진 graph들의 indegree를 -- 
            while(!q.empty()){
            
                int size = q.size();

                for(int s = 0; s< size; s++){
                    int preIdx = q.front();
                    q.pop();

                        for(int post : graph[preIdx]){ //후과목들을 뽑아서 //들어온 값과 같으면 수강가능 
                                indegree[post] --;

                                if(indegree[post] ==0) {
                                    q.push(post);
                                    completeCnt ++;
                        }
                }
            }
            Lcnt++;
        }
        
        if(completeCnt != N) {
            cout << "#" << t << " " << -1 << "\n";
        }else cout << "#" << t << " " << Lcnt << "\n";
    }
//}

}
