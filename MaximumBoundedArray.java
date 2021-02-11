//https://aonecode.com/interview-question/maximum-bounded-array

//Also known as winning sequence

public static int[] maximumBoundedArray(int n, int lowBound, int upBound){
        int result[] = new int[n];
        int diff = upBound-lowBound;
        if(diff < n/2){
            return new int[]{-1};
        }
        
        int mid = 0;
        if(n%2 == 1 && diff == n/2)
            mid = n / 2;
        else
            mid = n/2-1;

        result[mid] = upBound;
        for(int i = mid-1;i>=0;i--){
            result[i] = upBound - mid + i;
        }

        for(int i = mid; i < n; i++){
            result[i] = upBound - i + mid;
        }
        return result;
    }
